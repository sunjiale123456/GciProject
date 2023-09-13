package org.example;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.example.pojo.JsonData;
import org.example.pojo.PlanData;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        String csvFile = "C:\\Users\\information\\Desktop\\石家庄新计划验证\\平日计划.csv";
        String line = "";
        String csvDelimiter = ","; // CSV文件中的分隔符

        String dayType = "1-2-3-4-5";
        String busCode="";
        String fristTripDirection="0";
        String planTripTime="";
        String planTripBeginTime="";
        String planTripEndTime="";
        String direction="";


        HashMap<String, String[]> hashMap = new HashMap<>();

        try (
                BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvDelimiter);
                busCode=data[0];
                planTripTime="{\n"+
                        "\"dayType\":\"1-2-3-4-5\",\n"+
                        "\"busCode\": \""+busCode+"\",\n" +
                        "\"fristTripDirection\": \"0\",\n"+
                        "\"planTripTime\": [\n";
                // 在这里处理每一行的数据
                int flag=1;
                for (int i= 1;i<data.length;i=i+2) {
                    if(i%2==1&& i<data.length-1){
                        planTripBeginTime=data[i];
                        planTripEndTime=data[i+1];
                        direction = flag%2==1? "0":"1";
                        flag++;
                    }
                        planTripTime +=
                               "{\n" +
                                        "\"planTripBeginTime\":\"" + planTripBeginTime + "\",\n" +
                                        "\"planTripEndTime\":\"" + planTripEndTime + "\",\n" +
                                        "\"direction\":\"" + direction + "\" \n" +
                                        "},\n";

                }
                System.out.println(planTripTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createJson(){
        String jsonString = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}";

        // 将字符串转换为JSONObject
        JSONObject jsonObject = JSON.parseObject(jsonString);

        // 可以通过键获取对应的值
        String name = jsonObject.getString("name");
        int age = jsonObject.getIntValue("age");
        String city = jsonObject.getString("city");

        // 打印获取到的值
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
    @Test
    public void testDatatime(){
//        String time1 = "13:55";
//        String time2 = "16:30";
//        String time3 = "17:25";
//        String time4 = "19:05";
//
//        LocalTime t1 = LocalTime.parse(time1);
//        LocalTime t2 = LocalTime.parse(time2);
//        LocalTime t3 = LocalTime.parse(time3);
//        LocalTime t4 = LocalTime.parse(time4);
//
//        if (t1.isBefore(t2)) {
//            System.out.println(time1 + " is before " + time2);
//        } else if (t1.isAfter(t2)) {
//            System.out.println(time1 + " is after " + time2);
//        } else {
//            System.out.println(time1 + " is equal to " + time2);
//        }
//
//        if (t3.isBefore(t4)) {
//            System.out.println(time3 + " is before " + time4);
//        } else if (t3.isAfter(t4)) {
//            System.out.println(time3 + " is after " + time4);
//        } else {
//            System.out.println(time3 + " is equal to " + time4);
//        }
//
//        Duration duration = Duration.between(t1, t2);
//        int comparison = duration.compareTo(Duration.ZERO);
//        System.out.println(comparison);
//
//        long hours = duration.toHours();
//        long minutes = duration.toMinutes() % 60;
//        long seconds = duration.getSeconds() % 60;
//
//        System.out.println("Time difference: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
//
//        LocalDateTime dateTime1 = LocalDateTime.parse("2022-01-01T10:00:00");
//        LocalDateTime dateTime2 = LocalDateTime.parse("2022-01-02T15:30:00");
//
//        int comparisonDate = dateTime1.compareTo(dateTime2);
//        System.out.println(comparisonDate);
        LocalDateTime dateTime1 = LocalDateTime.parse("2023-06-20T07:42:47");
        LocalDateTime dateTime2 = LocalDateTime.parse("2023-06-20T08:49:02");

        Duration duration = Duration.between(dateTime1, dateTime2);

        long minutes = duration.toMinutes();

        System.out.println("Time difference in minutes: " + minutes);
    }
    @Test
    public void testJson(){
        String json="{\n" +
                "  \"cityId\": \"10001\",\n" +
                "  \"dataBase\": \"daas_sjz\",\n" +
                "  \"routeCode\": \"32\",\n" +
                "  \"intelligent_id\":\"123456789\",\n" +
                "  \"beginRange\": \"2023-06-20\",\n" +
                "  \"endRange\": \"2023-06-21\\n" +
                "}";
        JsonData jsonData = JSON.parseObject(json, JsonData.class);
        System.out.println(jsonData.toString());
    }

    @Test
    public void getBeanData(){
        // 设置Doris数据库连接信息
        String url = "jdbc:mysql://10.91.125.43:9030/daas_sjz";
        String username = "root";
        String password = "s#ab4s2e";

        // 创建查询语句
        String sql = "SELECT * FROM ods_dy_dispatch_plan limit 100";

        // 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();

        try {
            // 建立数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);

            // 创建ResultSetHandler，将查询结果转换为JavaBean对象列表
            ResultSetHandler<List<PlanData>> handler = new BeanListHandler<>(PlanData.class);

            // 执行查询
            List<PlanData> planDataList = queryRunner.query(connection, sql, handler);

            // 遍历打印查询结果
            for (PlanData planData : planDataList) {
                System.out.println(planData);
            }

            // 关闭数据库连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGethttp(){
        try {
            String json="6666";
            URL url = new URL("http://10.180.13.130:8090/op/trigger/"+json);
            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置请求方法
            conn.setRequestMethod("GET");

            // 发送请求
            int responseCode = conn.getResponseCode();

            // 检查响应码
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 处理JSON响应数据
                String jsonResponse = response.toString();
                System.out.println(jsonResponse);
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }

            // 关闭连接
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testTime(){
        // 创建日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 解析字符串为日期时间对象
        LocalDateTime datetimeBegin = LocalDateTime.parse("2023-09-13 08:34:35", formatter);
        LocalDateTime datetimeEnd = LocalDateTime.parse("2023-09-13 08:39:45", formatter);

        // 获取小时数
        DayOfWeek dayOfWeek = datetimeBegin.getDayOfWeek();
        int hour = datetimeBegin.getHour();
        int minute = datetimeBegin.getMinute();
        String hourStr = (hour>9?""+hour:"0"+hour);
        String fragment=(minute>=0&&minute<30?hourStr+":00":hourStr+":30");
        // 计算时间差
        Duration duration = Duration.between(datetimeBegin, datetimeEnd);

        // 获取周转时间 （分钟）
        long TripTimeMinute = duration.toMinutes() % 60;
        System.out.println(datetimeBegin);
        System.out.println(dayOfWeek.getValue());
        System.out.println(hour);
        System.out.println(hourStr);
        System.out.println(fragment);
        System.out.println(TripTimeMinute);
    }
    @Test
    public  void mainii() {

//        棉七小区	114.489668	38.035624
//        解放广场	114.483131	38.038559
//        解放广场	114.483131	38.038558
//        站前街南口	114.480155	38.036642
//        棉七小区	114.491313	38.03526
//        平安公园	114.496898	38.03506
//        省国资委（中核工程）	114.50124	38.034938
//        天津银行	114.507073	38.034812

//        二十二中	114.547597	38.035991
//        儿童医院西	114.539156	38.035816
        System.out.println(114.547597);
        System.out.println(38.035991);




        // ws84 转高德 longitude+0.00531
        // ws84 转高德  latitude-0.00271

        //高德转ws84   longitude-0.00531
      // 高德转ws84   latitude+0.00271

//        棉七小区	114.489668	38.035624  114.495885  38.036199  0.00622   locations":"114.495744900174,38.036319444445"}
//        解放广场	114.483131	38.038559  114.488291  38.040415  0.00516   locations":"114.489235026042,38.039272732205


//        https://restapi.amap.com/v3/assistant/coordinate/convert?locations=116.481499,39.990475&coordsys=gps&output=xml&key=<用户的key>

        //        二十二中	114.547597	38.035991 "locations":"114.55345296224,38.036527235244"
//        儿童医院西	114.539156	38.035816 "locations":"114.545038519966,38.036371256511"


        String origin = ""+(114.553453)+","+(38.036527); // 起点经纬度，例如：116.303955,39.976067
                String destination = ""+(114.545039)+","+(38.036371); // 终点经纬度，例如：116.303955,39.976067
                String key = "c7ab9df3d243b0e47dd530a7b37f1e4e"; // 高德地图API密钥

//        https://restapi.amap.com/v3/direction/transit/integrated?origin=116.481499,39.990475&destination=116.465063,39.999538&city=010&output=xml&key=<用户的key>
                try {
                    String url = "https://restapi.amap.com/v3/direction/transit/integrated?origin=" + URLEncoder.encode(origin, "UTF-8") +
                            "&destination=" + URLEncoder.encode(destination, "UTF-8") +
                            "&city=石家庄&output=json"+ "&key=" + URLEncoder.encode(key, "UTF-8");
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // 解析返回的JSON数据
                    String result = response.toString();
                    JSONObject object = (JSONObject) JSONObject.parse(result);
                    String code = object.getString("status");
                    // 提取距离值
                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
}
