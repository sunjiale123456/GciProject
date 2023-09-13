//package getCKdata;
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;;
//import org.apache.flink.table.api.EnvironmentSettings;
//import org.apache.flink.table.api.Table;
//import org.apache.flink.table.api.TableEnvironment;
//import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
//import org.apache.flink.types.Row;
//
//public class ClickHouseReadExample {
//    public static void main(String[] args) throws Exception {
//        // 创建流处理的执行环境
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        // 创建 ClickHouse 连接器的表环境
//        EnvironmentSettings settings = EnvironmentSettings.newInstance()
//                .useBlinkPlanner()
//                .inStreamingMode()
//                .build();
//
//        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);
//
//        // 配置 ClickHouse 连接参数
//        String url = "jdbc:clickhouse://localhost:8123";
//        String username = "your_username";
//        String password = "your_password";
//        String database = "your_database";
//        String table = "your_table";
//
//        ClickHouseOptions clickHouseOptions = ClickHouseOptionsFactory.createClickHouseOptions(url, username, password, database, table);
//
//        // 创建 ClickHouseSource
//        DataStream<Row> clickHouseSource = env.addSource(new ClickHouseSource(clickHouseOptions));
//
//        // 将数据流注册为表
//        tableEnv.registerDataStream("clickhouse_table", clickHouseSource, "col1, col2, col3");
//
//        // 执行 SQL 查询
//        String sql = "SELECT col1, col2 FROM clickhouse_table WHERE col3 > 10";
//
//        Table result = tableEnv.sqlQuery(sql);
//
//        // 转换为数据流并打印结果
//        DataStream<Tuple2<Boolean, Row>> outputStream = tableEnv.toRetractStream(result, Row.class);
//        outputStream.map(new MapFunction<Tuple2<Boolean, Row>, Row>() {
//            @Override
//            public Row map(Tuple2<Boolean, Row> value) throws Exception {
//                return value.f1;
//            }
//        }).print();
//
//        // 执行作业
//        env.execute("ClickHouse Read Example");
//    }
//}
