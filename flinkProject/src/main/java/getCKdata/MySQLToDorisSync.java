//package getCKdata;
//
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.common.restartstrategy.RestartStrategies;
//import org.apache.flink.api.common.serialization.SimpleStringSchema;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.connectors.doris.DorisSink;
//import org.apache.flink.streaming.connectors.doris.table.DorisSinkOptions;
//import org.apache.flink.streaming.connectors.doris.table.DorisTableSink;
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
//import org.apache.flink.table.api.EnvironmentSettings;
//import org.apache.flink.table.api.Table;
//import org.apache.flink.table.api.TableEnvironment;
//import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
//import org.apache.flink.table.api.bridge.java.internal.StreamTableEnvironmentImpl;
//import org.apache.flink.table.catalog.Catalog;
//import org.apache.flink.table.catalog.CatalogTable;
//import org.apache.flink.table.catalog.ObjectPath;
//import org.apache.flink.table.catalog.exceptions.DatabaseNotExistException;
//import org.apache.flink.table.catalog.exceptions.TableNotExistException;
//import org.apache.flink.table.catalog.exceptions.TableNotExistExceptionImpl;
//import org.apache.flink.table.catalog.exceptions.TableNotExistExceptionImplFactory;
//import org.apache.flink.table.catalog.exceptions.TableNotExistExceptionImplFactoryImpl;
//import org.apache.flink.table.catalog.exceptions.TableNotExistExceptionImplImpl;
//
//import java.util.Properties;
//
//public class MySQLToDorisSync {
//    public static void main(String[] args) throws Exception {
//        // 设置执行环境
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
//
//        // 设置重启策略
//        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3, 10000));
//
//        // 配置MySQL连接信息
//        Properties mysqlProperties = new Properties();
//        mysqlProperties.setProperty("database.url", "jdbc:mysql://localhost:3306/mydatabase");
//        mysqlProperties.setProperty("database.driver", "com.mysql.jdbc.Driver");
//        mysqlProperties.setProperty("database.username", "root");
//        mysqlProperties.setProperty("database.password", "password");
//
//        // 配置Doris连接信息
//        Properties dorisProperties = new Properties();
//        dorisProperties.setProperty("database.url", "jdbc:doris://localhost:9030/mydatabase");
//        dorisProperties.setProperty("database.driver", "com.mysql.jdbc.Driver");
//        dorisProperties.setProperty("database.username", "root");
//        dorisProperties.setProperty("database.password", "password");
//
//        // 创建MySQL表
//        String mysqlTableDDL = "CREATE TABLE mysql_table (\n" +
//                "  id INT,\n" +
//                "  name STRING\n" +
//                ") WITH (\n" +
//                "  'connector.type' = 'jdbc',\n" +
//                "  'connector.url' = '" + mysqlProperties.getProperty("database.url") + "',\n" +
//                "  'connector.table' = 'table_name',\n" +
//                "  'connector.driver' = '" + mysqlProperties.getProperty("database.driver") + "',\n" +
//                "  'connector.username' = '" + mysqlProperties.getProperty("database.username") + "',\n" +
//                "  'connector.password' = '" + mysqlProperties.getProperty("database.password") + "'\n" +
//                ")";
//
//        tableEnv.executeSql(mysqlTableDDL);
//
//        // 创建Doris表
//        String dorisTableDDL = "CREATE TABLE doris_table (\n" +
//                "  id INT,\n" +
//                "  name STRING\n" +
//                ") WITH (\n" +
//                "  'connector.type' = 'jdbc',\n" +
//                "  'connector.url' = '" + dorisProperties.getProperty("database.url") + "',\n" +
//                "  'connector.table' = 'table_name',\n" +
//                "  'connector.driver' = '" + dorisProperties.getProperty("database.driver") + "',\n" +
//                "  'connector.username' = '" + dorisProperties.getProperty("database.username") + "',\n" +
//                "  'connector.password' = '" + dorisProperties.getProperty("database.password") + "',\n" +
//                "  'connector.write.flush.interval' = '1s'\n" +
//                ")";
//
//        tableEnv.executeSql(dorisTableDDL);
//
//        // 执行CDC操作
//        String cdcSQL = "INSERT INTO doris_table SELECT * FROM mysql_table";
//
//        tableEnv.executeSql(cdcSQL);
//
//        // 启动作业
//        env.execute();
//    }
//}
