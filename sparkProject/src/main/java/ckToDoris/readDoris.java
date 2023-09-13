package ckToDoris;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class readDoris {
    public static void main(String[] args) {
        //获取配置
        SparkConf sparkConf = new SparkConf().setMaster("local[*]");
        // 创建SparkSession
        SparkSession spark = SparkSession.builder()
                .config(sparkConf)
                .appName("Doris reader")
                .getOrCreate();
        // 从Doris读取数据到DataFrame
        Dataset<Row> data = spark.read()
                .format("jdbc")
//                .option("driver", "com.mysql.cj.jdbc.Driver")
                .option("url", "jdbc:mysql://10.91.125.43:9030/test")
                .option("dbtable", " ods_dy_adreal  AS tmp")
                .option("user", "root")
                .option("password", "s#ab4s2e")
                .load();
        // 处理读取到的数据
        data.show();
    }
}
