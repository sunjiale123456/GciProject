package scalaWork
package loadClouseHouse

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{SaveMode, SparkSession}

object loadTCtoDoris {
  var sql = "select %s from csvView"
  var field = ""
  var fieldType = ""

  def main(args: Array[String]): Unit = {

    // 表字段顺序
    //    val strFiled = "triplog_code,pdate,bus_code,bus_plate,route_code,triplog_online,route_name,direction_name,begin_station_id,begin_station_name,end_station_id,end_station_name,begin_triplog_time,end_triplog_time,route_mileage,route_mileage_time,driver_name,driver_id,direction"
    //val strFiled="record_code,route_code,route_name,direction,station_id,station_name,station_order,into_station_time,out_station_time,obuid,plate_number,car_code"
    //val strFiled= "   card_number, card_type, transaction_money, transaction_time, route_code, obuid, car_code, pdate"
    //     val strFiled="bus_code,triplog_begin_station,plan_triplog_begin_time,plan_command_time,triplog_begin_time,triplog_begin_late,triplog_end_station,plan_triplog_end_time,triplog_end_time,triplog_end_late,plan_route_mileage,triplog_route_mileage,driver_name,emp_name,log_time,to_busstop,real_to_busstop,plan_datetime,route_code,route_run_type,direction,task_no,update_serial,real_driver_no,update_manual,log_note,route_name,shift_no,color_state,update_bus_no,distance_GPS,task_num,bypass_distance,serial,end_type"
    //    val strFiled = "triplog_id,run_date,obuid,number_plate,route_id,is_yingyun,route_name,direction,begin_station_id,begin_station_name,end_station_id,end_station_name,triplog_begin_time,triplog_end_time,run_mileage,run_duration,employee_id,employee_name,source_type"
    val strFiled="triplog_id,run_date,obuid,number_plate,route_id,is_yingyun,route_name,direction_name,begin_station_id,begin_station_name,end_station_id,end_station_name,triplog_begin_time,triplog_end_time,run_mileage,run_duration,employee_id,employee_name,source_type"
    //  val strFiled="`accessory`,`device_id`,`direction_id`,`driving_state`,`face_image_l`,`face_image_r`,`graffiti`,`illegal_state`,`impdate`,`insert_time`,`lane_no`,`lane_type`,`num_certificates`,`organ_name`,`pass_id`,`pass_time`,`pendant`,`pic_abbreviate`,`pic_plate`,`pilot_call`,`plate_color`,`plate_color_ex`,`plate_info`,`plate_info_ex`,`plate_struct`,`plate_type`,`plate_type_ex`,`plate_uniform`,`seatbelt`,`send_flag`,`sunvisor`,`tissue`,`tollgate_id`,`tollgate_name`,`trail_plate_color`,`trail_plate_info`,`upload_flag`,`vechcle_speed_limit`,`vechile_length`,`vehicle_appearance`,`vehicle_color`,`vehicle_color_ex`,`vehicle_func_id`,`vehicle_logo_ex`,`vehicle_model`,`vehicle_speed`,`vehicle_sublogo`,`vehicle_type`,`vehicle_type_ex`,`plate_info_ex_length`,`plate_info_length`,`with_sunroof`"
    val str = strFiled.replace(" ", "")
    //文件路径地址
    val csvPath = "C:\\Users\\information\\Desktop\\石家庄新计划验证\\roadsheet\\*.txt"

    //设置sql去除每个字段的空格
    val strArray = str.split(",")
    for (i <- 0 to strArray.length - 1) {
      if (i == 0) {
        field = " trim(" + strArray(i) + ") as " +strArray(i);
        fieldType = strArray(i) + ":String";
      } else {
        field = field + ",trim(" + strArray(i) + ") as " + strArray(i);
        fieldType = fieldType + "," + strArray(i) + ":String";
      }
    }
    val strSql = String.format(sql, field)
    println(strSql)
    //Spark集群配置的各种参数
    val sparkConf = new SparkConf();
    //SparkContext的初始化需要一个SparkConf对象
    //        sparkConf.set("spark.testing.memory", "2147480000")
    //    获取SparkSession
    val spark = SparkSession.builder().appName("SQLTest").master("local[*]").config(sparkConf).getOrCreate()

    //创建执行环境http://172.31.200.171:8201/10.91.125.7:8889
    // 10.91.125.7:8889 8020

    //10.91.125.7   node1.test.com node1
    //10.91.125.11   node2.test.com node2
    //10.91.125.12   node3.test.com node3
    //10.91.125.13   node4.test.com node4
    //10.91.125.15   node5.test.com node5
    //
    //    val spark: SparkSession = SparkSession.builder()
    //      .config("hive.metastore.uris", "thrift://10.91.125.8:9083")
    ////      .config("hive.metastore.uris", "thrift://10.91.125.7:8020")
    ////      .config("spark.sql.warehouse.dir", "hdfs://10.91.125.7:8020/user/hive/warehouse")
    //      //执行本地
    //      .master("local")
    //      //名称
    //      .appName("data")
    //      //必须加enableHiveSupport()，这样才可以往hive数据表写数据
    //      .enableHiveSupport()
    //      .getOrCreate()
    //
    val csvDf = spark.read
      .option("delimiter", ",") //分隔符，默认为逗号, , \t \t+
      .option("header", "false") // 首行数据 true 就是不要
      .option("quote", "") //引号字符，默认为双引号"  将csv每个字段的引号删除只要数据
      //      .option("nullValue", "\\N") //第一行不作为数据内容，作为标题
      .option("inferSchema", "false") //自动推测字段类型
      .option("encoding", "gbk")//utf-8 ,gbk
      //      .option("ignoreLeadingWhiteSpace","true")
      //     .option("nignoreTrailingWhiteSpace","true")
      .schema(ScalaReflection.schemaFor[UserDetail6].dataType.asInstanceOf[StructType]) //指定csv字段类型
      .csv(csvPath)

    //设置sprark临时表
    csvDf.createTempView("csvView")
    //使用sparkSQL进行临时表数据
    val frame = spark.sql(strSql)
    frame.createTempView("frameTable")
    val frame1 = spark.sql("select \n'' as province_id,\n'河北' as province_name,\n'' as city_id,\n'石家庄' as city_name,\ntriplog_id,\nobuid as bus_id,\n'' as change_info,\n'' as depart_interval,\nif(direction_name='上行','0','1') direction,\nemployee_id,\nemployee_name,\nbegin_station_id as from_station_id,\nbegin_station_name as from_station_name,\nnumber_plate,\n'' as plan_time,\n'' as record_status,\nroute_id as route_id_belong_to,\nroute_id as route_id_run,\nroute_id as route_run_code,\nroute_id as route_belongto_code,\nroute_name as route_name_belong_to,\nroute_name as route_name_run,\n'' as route_sub_id,\n'' as route_sub_name,\nrun_date,\nrun_duration,\nrun_mileage,\n'' as running_no,\nsource_type service_type,\nend_station_id as to_station_id,\nend_station_name as to_station_name,\n'' as auto_begin_time,\n'' as auto_end_time,\ntriplog_begin_time,\ntriplog_end_time,\nobuid as bus_code,\n'' as qualification,\n'' as route_trips_type,\n'' as on_time_type,\n'' as running_log,\n'1' as trip_type,\n'' as dispatch_time,\n'' as dispatch_sent_time,\n'1' as trips,\nobuid,\n'石家庄公交' as organ_id,\norgan_name,\nbus_name,\n'全程' service_name,\n'' as gps_begin_time,\n'' as gps_mileage,\n'' as remark,\nDATE_FORMAT(run_date, 'yyyy-MM-dd') as pdate \nfrom frameTable")
    //    frame.show()
    //写入hive数据库的操作，Overwrite覆盖 Append追加，ErrorIfExists存在保报错，Ignore：如果存在就忽略
    //    frame.write.mode(SaveMode.Append).format("hive").saveAsTable("ods_db.ods_qtga_kkgcsjj_tm")//
    //动态分区
    //因为要做动态分区, 所以要先设定partition参数
    //由于default是false, 需要额外下指令打开这个开关
    //    spark sql ("set hive.exec.dynamic.partition=true")
    //    spark sql ("set hive.exec.dynamic.partition.mode=nostrick")
    //指定分区字段到分区表中
    //    frame.write.mode(SaveMode.Append).format("hive").partitionBy("pdate").saveAsTable("ods_db.ods_qtga_kkgcsjj_tm")

    frame1.show()
    //新建配置类
//    val connProperties = new Properties
//    connProperties.setProperty("driver", "ru.yandex.clickhouse.ClickHouseDriver")
//    connProperties.setProperty("user", "default")
//    connProperties.setProperty("password", "Gds!23d3")
//    connProperties.setProperty("database", "bigdatadb_sjz")
//    frame.write.mode(SaveMode.Append).option("batchsize", "100000")
//      .jdbc("jdbc:clickhouse://10.91.125.4:8123", "ods_bus_triplog_origin_datax", connProperties)
    //关闭SparkSession
    //    val url="jdbc:hive2://10.91.125.7:10000/ods_db"
    //    val connProperties = new Properties
    //      connProperties.setProperty("driver", "org.apache.hive.jdbc.HiveDriver")
    //      connProperties.setProperty("url", "jdbc:hive2://10.91.125.7:10000/ods_db")
    //      connProperties.setProperty("user", "hive")
    //      connProperties.setProperty("password", "Admin@szb2022")
    //      connProperties.setProperty("fetchsize", "2000")
    //      connProperties.setProperty("dbtable", "ods_qtga_kkgcsjj_tm")
    ////      csvDf.write.jdbc(url,"ods_qtga_kkgcsjj_tm","a",connProperties)
    //    //              .jdbc("jdbc:clickhouse://10.91.125.4:8123", "ods_bus_triplog_origin_datax_test", connProperties)
    //
    //
    //    val value = spark.read.format("jdbc")
    //      .option("driver", "org.apache.hive.jdbc.HiveDriver")
    //      .option("url", "jdbc:hive2://10.91.125.7:10000/ods_db")
    //      .option("user", "hive")
    //      .option("password", "Admin@szb2022")
    //      .option("fetchsize", "2000")
    //      .option("dbtable", "ods_qtga_kkgcsjj_tm")
    //      .load()
    //    value.show(10)

    //    val frame1 = spark.sql(
    //      """
    //        | insert overwrite table abc partition('2023-07-21')
    //        | select * from qwe
    //        |""".stripMargin)
    //    frame1.show()

    //    csvDf.createTempView("ods_qtga_kkgcsjj_tm")
    //    val frame = spark.sql(s"select ods_qtga_kkgcsjj_tm.`accessory`,ods_qtga_kkgcsjj_tm.direction_id,ods_qtga_kkgcsjj_tm.driving_state,ods_qtga_kkgcsjj_tm.face_image_l,ods_qtga_kkgcsjj_tm.face_image_r,ods_qtga_kkgcsjj_tm.graffiti,ods_qtga_kkgcsjj_tm.illegal_state,ods_qtga_kkgcsjj_tm.impdate,ods_qtga_kkgcsjj_tm.insert_time,ods_qtga_kkgcsjj_tm.lane_no,ods_qtga_kkgcsjj_tm.lane_type,ods_qtga_kkgcsjj_tm.num_certificates,ods_qtga_kkgcsjj_tm.organ_name,ods_qtga_kkgcsjj_tm.pass_id,ods_qtga_kkgcsjj_tm.pass_time,ods_qtga_kkgcsjj_tm.pendant,ods_qtga_kkgcsjj_tm.pic_abbreviate,ods_qtga_kkgcsjj_tm.pic_plate,ods_qtga_kkgcsjj_tm.pilot_call,ods_qtga_kkgcsjj_tm.plate_color,ods_qtga_kkgcsjj_tm.plate_color_ex,ods_qtga_kkgcsjj_tm.plate_info,ods_qtga_kkgcsjj_tm.plate_info_ex,ods_qtga_kkgcsjj_tm.plate_struct,ods_qtga_kkgcsjj_tm.plate_type,ods_qtga_kkgcsjj_tm.plate_type_ex,ods_qtga_kkgcsjj_tm.plate_uniform,ods_qtga_kkgcsjj_tm.seatbelt,ods_qtga_kkgcsjj_tm.send_flag,ods_qtga_kkgcsjj_tm.sunvisor,ods_qtga_kkgcsjj_tm.tissue,ods_qtga_kkgcsjj_tm.tollgate_id,ods_qtga_kkgcsjj_tm.tollgate_name,ods_qtga_kkgcsjj_tm.trail_plate_color,ods_qtga_kkgcsjj_tm.trail_plate_info,ods_qtga_kkgcsjj_tm.upload_flag,ods_qtga_kkgcsjj_tm.vechcle_speed_limit,ods_qtga_kkgcsjj_tm.vechile_length,ods_qtga_kkgcsjj_tm.vehicle_appearance,ods_qtga_kkgcsjj_tm.vehicle_color,ods_qtga_kkgcsjj_tm.vehicle_color_ex,ods_qtga_kkgcsjj_tm.vehicle_func_id,ods_qtga_kkgcsjj_tm.vehicle_logo_ex,ods_qtga_kkgcsjj_tm.vehicle_model,ods_qtga_kkgcsjj_tm.vehicle_speed,ods_qtga_kkgcsjj_tm.vehicle_sublogo,ods_qtga_kkgcsjj_tm.vehicle_type,ods_qtga_kkgcsjj_tm.vehicle_type_ex,ods_qtga_kkgcsjj_tm.plate_info_ex_length,ods_qtga_kkgcsjj_tm.plate_info_length,ods_qtga_kkgcsjj_tm.with_sunroof,ods_qtga_kkgcsjj_tm.pdate from ods_qtga_kkgcsjj_tm")
    //    frame.show()

    //    unit.select("accessory","direction_id","driving_state","face_image_l","face_image_r","graffiti","illegal_state","impdate","insert_time","lane_no","lane_type","num_certificates","organ_name","pass_id","pass_time","pendant","pic_abbreviate","pic_plate","pilot_call","plate_color","plate_color_ex","plate_info","plate_info_ex","plate_struct","plate_type","plate_type_ex","plate_uniform","seatbelt","send_flag","sunvisor","tissue","tollgate_id","tollgate_name","trail_plate_color","trail_plate_info","upload_flag","vechcle_speed_limit","vechile_length","vehicle_appearance","vehicle_color","vehicle_color_ex","vehicle_func_id","vehicle_logo_ex","vehicle_model","vehicle_speed","vehicle_sublogo","vehicle_type","vehicle_type_ex","plate_info_ex_length","plate_info_length","with_sunroof","pdate")
    //    frame.write
    //      .mode("append")
    //      .format("jdbc")
    //      .option("driver", "org.apache.hive.jdbc.HiveDriver")
    //      .option("url", "jdbc:hive2://10.91.125.7:10000/ods_db")
    //      .option("dbtable", "ods_qtga_kkgcsjj_tm01")
    //      .option("user", "hive")
    //      .option("password", "Admin@szb2022")
    //      .option("fetchsize", "2000")
    //      .save()

//    frame.write
//      .format("jdbc")
//      // 新版本驱动 com.mysql.cj.jdbc.Driver   老版本驱动 com.mysql.jdbc.Driver
//      //                .option("driver", "com.mysql.cj.jdbc.Driver")
//      .option("url", "jdbc:mysql://10.91.125.43:9030/test")
//      .option("dbtable", "ods_dy_adreal_test")
//      .option("user", "root")
//      .option("password", "s#ab4s2e")
//      .option("partitionColumn", "pdate")  // 指定分区列名称
//      .option("lowerBound", "2020-08-01")  // 指定分区下界
//      .option("upperBound", "2023-08-31")  // 指定分区上界
//      .option("numPartitions", "1")  // 指定分区数
//      .option("batchsize", "10000")
//      .mode(SaveMode.Append)  // 根据需求选择SaveMode
//      .save();



    spark.stop()
  }

  case class UserDetail(user_id: Int, sale_amount: Double, trans_count: Int, offline_count: Int, online_count: Int, shopping_count: Int, tuihuo_count: Int, tuihuo_lv: Double, apru: Double, create_day: Int, is_gravida: Int, is_dobule_source: Int, baby_day: Int, active_code: Int) {}

  // 临沂进出站数据 bigdatadb_ly.ods_into_out_station
  case class UserDetail1(record_code: String,
                         route_code: String,
                         route_name: String,
                         direction: String,
                         station_id: String,
                         station_name: String,
                         station_order: String,
                         into_station_time: String,
                         out_station_time: String,
                         obuid: String,
                         plate_number: String,
                         car_code: String
                        ) {}

  // 临沂路单数数据 ods_raw_dy_triplog
  case class UserDetail2(
                          triplog_code: String,
                          pdate: String,
                          bus_code: String,
                          bus_plate: String,
                          route_code: String,
                          triplog_online: String,
                          route_name: String,
                          direction_name: String,
                          begin_station_id: String,
                          begin_station_name: String,
                          end_station_id: String,
                          end_station_name: String,
                          begin_triplog_time: String,
                          end_triplog_time: String,
                          route_mileage: String,
                          route_mileage_time: String,
                          driver_name: String,
                          driver_id: String,
                          direction: String
                        ) {}

  // 临沂交易数据 ods_swipe_transaction
  case class UserDetail3(
                          card_number: String,
                          card_type: String,
                          transaction_money: String,
                          transaction_time: String,
                          route_code: String,
                          obuid: String,
                          car_code: String,
                          pdate: String
                        ) {}


  // 路单数据  临沂老数据 ods_apts_triplog
  case class UserDetail4(
                          triplog_code: String,
                          pdate: String,
                          bus_code: String,
                          bus_plate: String,
                          route_code: String,
                          triplog_online: String,
                          route_name: String,
                          direction_name: String,
                          begin_station_id: String,
                          begin_station_name: String,
                          end_station_id: String,
                          end_station_name: String,
                          begin_triplog_time: String,
                          end_triplog_time: String,
                          route_mileage: String,
                          route_mileage_time: String,
                          driver_name: String,
                          driver_id: String,
                          direction: String
                        ) {}

  // 临沂电子路单 ods_raw_dy_triplog_test
  case class UserDetail5(
                          bus_code: String,
                          triplog_begin_station: String,
                          plan_triplog_begin_time: String,
                          plan_command_time: String,
                          triplog_begin_time: String,
                          triplog_begin_late: String,
                          triplog_end_station: String,
                          plan_triplog_end_time: String,
                          triplog_end_time: String,
                          triplog_end_late: String,
                          plan_route_mileage: String,
                          triplog_route_mileage: String,
                          driver_name: String,
                          emp_name: String,
                          log_time: String,
                          to_busstop: String,
                          real_to_busstop: String,
                          plan_datetime: String,
                          route_code: String,
                          route_run_type: String,
                          direction: String,
                          task_no: String,
                          update_serial: String,
                          real_driver_no: String,
                          update_manual: String,
                          log_note: String,
                          route_name: String,
                          shift_no: String,
                          color_state: String,
                          update_bus_no: String,
                          distance_GPS: String,
                          task_num: String,
                          bypass_distance: String,
                          serial: String,
                          end_type: String
                        ){}

  // 石家庄 路单数据 老系统
  case class UserDetail6(
                          triplog_id         :String,
                          run_date           :String,
                          obuid              :String,
                          number_plate       :String,
                          route_id           :String,
                          is_yingyun         :String,
                          route_name         :String,
                          direction_name     :String,
                          begin_station_id   :String,
                          begin_station_name :String,
                          end_station_id     :String,
                          end_station_name   :String,
                          triplog_begin_time :String,
                          triplog_end_time   :String,
                          run_mileage        :String,
                          run_duration       :String,
                          employee_id        :String,
                          employee_name      :String,
                          source_type        :String
                        ) {}


  //石家庄 刷卡
  case class UserDetail7(
                          logcardid: String,
                          billno: String,
                          cardtype_name: String,
                          cardtype: String,
                          thtime: String,
                          thno: String,
                          trade_momey: String,
                          price: String,
                          remain_momey: String,
                          card_category: String,
                          organ_id: String,
                          organ_name: String,
                          fleet_id: String,
                          fleet_name: String,
                          route_id: String,
                          route_name: String,
                          bus_no: String,
                          number_plate: String,
                          obuid: String,
                          tradeno: String,
                          station_name: String,
                          longitude: String,
                          latitude: String,
                          consumer_no: String,
                          main_card_type: String,
                          sub_card_type: String ){}

  case class UserDetail10(
                           accessory : String ,
                           device_id : String ,
                           direction_id : String ,
                           driving_state : String ,
                           face_image_l : String ,
                           face_image_r : String ,
                           graffiti : String ,
                           illegal_state : String ,
                           impdate : String ,
                           insert_time : String ,
                           lane_no : String ,
                           lane_type : String ,
                           num_certificates : String ,
                           organ_name : String ,
                           pass_id : String ,
                           pass_time : String ,
                           pendant : String ,
                           pic_abbreviate : String ,
                           pic_plate : String ,
                           pilot_call : String ,
                           plate_color : String ,
                           plate_color_ex : String ,
                           plate_info : String ,
                           plate_info_ex : String ,
                           plate_struct : String ,
                           plate_type : String ,
                           plate_type_ex : String ,
                           plate_uniform : String ,
                           seatbelt : String ,
                           send_flag : String ,
                           sunvisor : String ,
                           tissue : String ,
                           tollgate_id : String ,
                           tollgate_name : String ,
                           trail_plate_color : String ,
                           trail_plate_info : String ,
                           upload_flag : String ,
                           vechcle_speed_limit : String ,
                           vechile_length : String ,
                           vehicle_appearance : String ,
                           vehicle_color : String ,
                           vehicle_color_ex : String ,
                           vehicle_func_id : String ,
                           vehicle_logo_ex : String ,
                           vehicle_model : String ,
                           vehicle_speed : String ,
                           vehicle_sublogo : String ,
                           vehicle_type : String ,
                           vehicle_type_ex : String ,
                           plate_info_ex_length : String ,
                           plate_info_length : String ,
                           with_sunroof : String ,
                           pdate : String
                         ) {}
}



