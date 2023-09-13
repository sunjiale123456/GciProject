package org.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GaodeCoordinateConverter {
    public static void main(String[] args) {
        String apiKey = "c7ab9df3d243b0e47dd530a7b37f1e4e";
        //	114.489668	38.035624  114.495885  38.036199  0.00622
        // 114.483131	38.038559

//        二十二中	114.547597	38.035991 "locations":"114.55345296224,38.036527235244"
//        儿童医院西	114.539156	38.035816 "locations":"114.545038519966,38.036371256511"

        double latitude = 38.035816;
        double longitude = 114.539156;

        try {
            String url = "https://restapi.amap.com/v3/assistant/coordinate/convert?" +
                            "locations=" + URLEncoder.encode(longitude + "," + latitude, "UTF-8") +
                            "&coordsys=gps" +
                            "&output=json" +
                            "&key=" + apiKey;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                System.out.println(response.toString());
            } else {
                System.out.println("请求失败，错误代码：" + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
