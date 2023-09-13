package getCKdata;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class test {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> sourceStream = env.socketTextStream("10.91.125.60", 6667);
        SingleOutputStreamOperator<Tuple2<String, Integer>> map = sourceStream.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) throws Exception {
                String[] split = s.split(",");
                Tuple2<String, Integer> tpu2 = new Tuple2<>();
                for (int i = 0; i < split.length; i++) {
                    tpu2.setField(split[i], i);
                }
                return tpu2;
            }
        });
        map.print();

        env.execute();

    }
}
