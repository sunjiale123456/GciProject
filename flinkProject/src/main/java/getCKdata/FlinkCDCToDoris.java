package getCKdata;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.api.bridge.java.internal.StreamTableEnvironmentImpl;

import java.util.Properties;

public class FlinkCDCToDoris {
    public static void main(String[] args) throws Exception {
        // 设置执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        // 设置重启策略
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, 10000));

        // 配置CDC连接信息
        Properties cdcProperties = new Properties();
        cdcProperties.setProperty("database.server.name", "mysql_server");
        cdcProperties.setProperty("database.hostname", "10.91.125.8");
        cdcProperties.setProperty("database.port", "3307");
        cdcProperties.setProperty("database.user", "root");
        cdcProperties.setProperty("database.password", "Gci@123456");
        cdcProperties.setProperty("database.whitelist", "daas3");
//        cdcProperties.setProperty("database.history.kafka.bootstrap.servers", "localhost:9092");
//        cdcProperties.setProperty("database.history.kafka.topic", "cdc_topic");

        // 配置Doris连接信息
        Properties dorisProperties = new Properties();
        dorisProperties.setProperty("database.url", "jdbc:mysql://10.91.125.43:9030/test");
        dorisProperties.setProperty("database.driver", "com.mysql.jdbc.Driver");
        dorisProperties.setProperty("database.username", "root");
        dorisProperties.setProperty("database.password", "s#ab4s2e");

        // 创建CDC表
        String cdcTableDDL = "CREATE TABLE cdc_table (\n" +
                "  id INT PRIMARY KEY,\n" +
                "  name STRING,\n" +
                "  age Int,\n" +
                "  email bigint\n"+
                ") WITH (\n" +
                "  'connector' = 'mysql-cdc',\n" +
                "  'hostname' = '" + cdcProperties.getProperty("database.hostname") + "',\n" +
                "  'port' = '" + cdcProperties.getProperty("database.port") + "',\n" +
                "  'username' = '" + cdcProperties.getProperty("database.user") + "',\n" +
                "  'password' = '" + cdcProperties.getProperty("database.password") + "',\n" +
                "  'database-name' = 'daas3',\n" +
                "  'table-name' = 'testStudent'\n" +
//                "  'debezium-properties.bootstrap.servers' = '" + cdcProperties.getProperty("database.history.kafka.bootstrap.servers") + "',\n" +
//                "  'debezium-properties.group.id' = 'flink-cdc',\n" +
//                "  'debezium-properties.key.converter' = 'org.apache.kafka.connect.json.JsonConverter',\n" +
//                "  'debezium-properties.value.converter' = 'org.apache.kafka.connect.json.JsonConverter',\n" +
//                "  'debezium-properties.key.converter.schemas.enable' = 'false',\n" +
//                "  'debezium-properties.value.converter.schemas.enable' = 'false',\n" +
//                "  'debezium-properties.database.history.kafka.topic' = '" + cdcProperties.getProperty("database.history.kafka.topic") + "',\n" +
//                "  'debezium-properties.database.history.kafka.bootstrap.servers' = '" + cdcProperties.getProperty("database.history.kafka.bootstrap.servers") + "'\n" +
                ")";

        tableEnv.executeSql(cdcTableDDL);

        // 创建Doris表
        String dorisTableDDL = "CREATE TABLE doris_table (\n" +
                "  `id` Int,\n" +
                "  `name` STRING,\n" +
                "  `age` STRING,\n" +
                "  `count` BIGINT\n" +
                ") WITH (\n" +
                "  'connector.type' = 'jdbc',\n" +
                "  'connector.url' = '" + dorisProperties.getProperty("database.url") + "',\n" +
                "  'connector.table' = 'student',\n" +
                "  'connector.driver' = '" + dorisProperties.getProperty("database.driver") + "',\n" +
                "  'connector.username' = '" + dorisProperties.getProperty("database.username") + "',\n" +
                "  'connector.password' = '" + dorisProperties.getProperty("database.password") + "',\n" +
                "  'connector.write.flush.interval' = '1s'\n" +
                ")";

        tableEnv.executeSql(dorisTableDDL);

        // 执行CDC操作
//        String cdcSQL = "INSERT INTO doris_table SELECT * FROM cdc_table";

        tableEnv.executeSql("select * from doris_table limit 100").print();
//        tableEnv.executeSql(cdcSQL);

        // 启动作业
        env.execute();
    }
}

