package ckToDoris;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;

public class CKToDorisWriter {
    public static void main(String[] args)  {
        //获取配置
        SparkConf sparkConf = new SparkConf().setMaster("local[*]");
        // 创建SparkSession
        SparkSession spark = SparkSession.builder()
                .config(sparkConf)
                .config("spark.doris.connectionTimeout", "600000")  // 设置连接超时时间为 10分钟
                .appName("CK to Doris Writer")
                .getOrCreate();

        // 读取ClickHouse数据

        // 构建ClickHouse查询语句
        String query = "SELECT * FROM ods_raw_dy_adreal where ad_time !=''";

        Dataset<Row> ckData = spark.read()
                .format("jdbc")
                .option("driver","ru.yandex.clickhouse.ClickHouseDriver")
                .option("url", "jdbc:clickhouse://10.91.125.4:8123/bigdatadb_cqsj")
                .option("user", "default")
                .option("password", "Gds!23d3")
                .option("query", query)
                .load();
//
//        ckData.createOrReplaceTempView("tableTemp");
//        Dataset<Row> dataset = spark.sql("select * from tableTemp");

        // 将字符串转为 datetime形式 yyyy-MM-dd HH:mm:ss
        // dataset = dataset.withColumn("pdate", functions.to_timestamp(dataset.col("ad_time"), "yyyy-MM-dd"));
        // 将字符转为 date 形式 yyyy-MM-dd
        ckData = ckData.withColumn("pdate", functions.to_date(ckData.col("ad_time"), "yyyy-MM-dd"));

        ckData.show();
        // 写入数据到Doris
        ckData.write()
                .format("jdbc")
                // 新版本驱动 com.mysql.cj.jdbc.Driver   老版本驱动 com.mysql.jdbc.Driver
//                .option("driver", "com.mysql.cj.jdbc.Driver")
                .option("url", "jdbc:mysql://10.91.125.43:9030/test")
                .option("dbtable", "ods_dy_adreal_test")
                .option("user", "root")
                .option("password", "s#ab4s2e")
                .option("partitionColumn", "pdate")  // 指定分区列名称
                .option("lowerBound", "2020-08-01")  // 指定分区下界
                .option("upperBound", "2023-08-31")  // 指定分区上界
                .option("numPartitions", "1")  // 指定分区数
                .option("batchsize", "10000")
                .mode(SaveMode.Append)  // 根据需求选择SaveMode
                .save();


        // 停止SparkSession
        spark.stop();
    }
}
