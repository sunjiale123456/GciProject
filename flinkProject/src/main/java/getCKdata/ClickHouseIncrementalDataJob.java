//package getCKdata;
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.connectors.clickhouse.ClickHouseRowConverter;
//import org.apache.flink.streaming.connectors.clickhouse.table.RowDataClickHouseSinkFunction;
//import org.apache.flink.streaming.connectors.clickhouse.table.RowDataClickHouseSourceFunction;
//import org.apache.flink.streaming.connectors.clickhouse.table.internal.options.ClickHouseOptions;
//import org.apache.flink.table.api.DataTypes;
//import org.apache.flink.table.api.TableSchema;
//import org.apache.flink.table.types.DataType;
//import org.apache.flink.table.types.logical.RowType;
//
//public class ClickHouseIncrementalDataJob {
//
//    public static void main(String[] args) throws Exception {
//        // 创建执行环境
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        // 设置ClickHouse连接参数
//        ClickHouseOptions options = ClickHouseOptions.builder()
//                .setHost("10.91.125.4")
//                .setPort(8123)
//                .setDatabase("bigdatadb_ly")
//                .setTable("test")
//                .build();
//
//        // 定义表结构
//        TableSchema tableSchema = TableSchema.builder()
//                .field("uuu", DataTypes.STRING())
//                .field("id", DataTypes.STRING())
//                .field("name", DataTypes.STRING())
//                .field("age", DataTypes.INT())
//                .build();
//
//        // 创建ClickHouse的Source函数
//        RowDataClickHouseSourceFunction sourceFunction = new RowDataClickHouseSourceFunction(
//                options,
//                tableSchema,
//                new MyRowConverter()
//        );
//
//        // 添加Source函数获取增量数据
//        DataStream<Tuple2<Boolean, RowData>> dataStream = env.addSource(sourceFunction);
//
//        // 处理增量数据
//        dataStream.map(new MyMapFunction())
//                .print();
//
//        // 执行作业
//        env.execute("ClickHouse Incremental Data Job");
//    }
//
//    // 自定义RowConverter，将RowData转换为ClickHouse的行对象
//    public static class MyRowConverter implements ClickHouseRowConverter {
//        @Override
//        public Tuple2<Boolean, Object[]> convert(RowData rowData) {
//            // 实现转换逻辑，将RowData转换为ClickHouse的行对象
//            // 返回的Tuple2的第一个元素表示是否为插入操作，第二个元素为行对象的数据
//            return null;
//        }
//    }
//
//    // 自定义MapFunction，处理增量数据
//    public static class MyMapFunction implements MapFunction<Tuple2<Boolean, RowData>, String> {
//        @Override
//        public String map(Tuple2<Boolean, RowData> value) throws Exception {
//            // 处理增量数据的逻辑
//            // 返回处理结果
//            return value.toString();
//        }
//    }
//}
