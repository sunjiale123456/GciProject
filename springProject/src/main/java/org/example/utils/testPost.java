package org.example.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class testPost {
    public static void main(String[] args) {
        String url = "http://10.180.13.130:8090/op/trigger";
        String jsonBody = "{\n" +
                "  \"cityId\": \"10001\",\n" +
                "  \"dataBase\": \"daas_sjz\",\n" +
                "  \"routeCode\": \"32\",\n" +
                "  \"intelligent_id\":\"123456789\",\n" +
                "  \"beginRange\": \"2023-06-01\",\n" +
                "  \"endRange\": \"2023-06-05\"\n" +
                "}";  // JSON请求体

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json");

            // 发送JSON请求体数据
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonBody.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            System.out.println("Response Body: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
