package org.example.service;

import com.alibaba.fastjson.JSON;

import io.vavr.Tuple3;
import io.vavr.Tuple4;
import org.example.pojo.JsonData;

import org.example.pojo.PlanData;
import org.example.pojo.TripData;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Calculate {

    public List<TripData> TripDataList = new ArrayList<>();
    public List<PlanData> PlanDataList = new ArrayList<>();
    // 创建日期时间格式化器
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    public Calculate(String json) {
         // TODO:将字符转为 json对象
        JsonData jsonData = JSON.parseObject(json, JsonData.class);
        // TODO:获取路单数据
        this.TripDataList =  new GetDorisTrip().getResult(jsonData);
        // TODO:获取计划表数据
        this.PlanDataList =  new GetDorisPlan().getResult(jsonData);
    }
     // TODO：初始化
    public void init() {
        compute();
    }
    /*
    *   路单表 和计划表 车辆进行匹配
    */
    // 计算计划表中 周期内 每个方向 每辆车的 首次发车时间和顺序
    public void planOrder(){

    }
    // 计算路单表中 每天的 时段（30minute） 平均周转时间
    public HashMap<String, Integer> GetFragment(){
        HashMap<String, List<Integer>> hashMap = new HashMap<>();
        HashMap<String, Integer> tripTimeMap = new HashMap<>();

        for (int i = 0; i < TripDataList.size(); i++) {

            String routeCode = TripDataList.get(i).getRoute_id_belong_to();
            String direction = TripDataList.get(i).getDirection();
            String tripBeginTime = TripDataList.get(i).getTriplog_begin_time();
            String tripEndTime = TripDataList.get(i).getTriplog_end_time();
            String pDate = TripDataList.get(i).getPdate();

            // 解析字符串为日期时间对象
            LocalDateTime datetimeBegin = LocalDateTime.parse(tripBeginTime, formatter);
            LocalDateTime datetimeEnd = LocalDateTime.parse(tripEndTime, formatter);
            // 周期数
            int dayOfWeek = datetimeBegin.getDayOfWeek().getValue();
            // 获取小时数
            int hour = datetimeBegin.getHour();
            int minute = datetimeBegin.getMinute();
            String hourStr = (hour > 9 ? "" + hour : "0" + hour);
            String fragment = (minute >= 0 && minute < 30 ? hourStr + ":00" : hourStr + ":30");

            // 计算时间差
            Duration duration = Duration.between(datetimeBegin, datetimeEnd);
            // 获取周转时间 （分钟）
            long TripTimeMinute = duration.toMinutes() ;
            int intValue =0;
            try {
                intValue = Math.toIntExact(TripTimeMinute);
            } catch (ArithmeticException e) {
                System.out.println("转换失败：超出int类型的取值范围");
            }

            // 创建key值
            String keyData = pDate+"&"+routeCode + "&" + direction + "&" + fragment+"&"+dayOfWeek;

            // 根据方向时段 收集 周转时间
            if (hashMap.containsKey(keyData)) {
                List<Integer> list = hashMap.get(keyData);
                list.add(intValue);
                hashMap.put(keyData, list);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(intValue);
                hashMap.put(keyData, list);
            }
        }
            // 根据方向时段 计算 平均周转时间
            for (String Temp : hashMap.keySet()){
                List<Integer> tripTime = hashMap.get(Temp);
                long tripTimeSum = 0;
                for(Integer data : tripTime) {
                    tripTimeSum+=data;
                }
                if(tripTime.size()!=0) {
                    long avgTripTime = tripTimeSum / tripTime.size();
                    tripTimeMap.put(Temp, (int) avgTripTime);
                }
            }
        return tripTimeMap;
    }
    // 合并数据，以计划表为主表 关联 每一天时段周转时间 模拟运行
    public HashMap<String, List<Tuple3<LocalTime, LocalTime, String>>> planSort(){
        HashMap<String, List<Tuple3<LocalTime, LocalTime, String>>> planTimeMap = new HashMap<>();
        for(PlanData planData: PlanDataList){
            String routeCode = planData.getRoute_code();
            String direction = planData.getDirection();
            String bus_id = planData.getBus_id();
            String plan_type = planData.getPlan_type();

            LocalDateTime planBeginTime = planData.getPlan_trip_begin_time();
            String beginTimeStr = planBeginTime.format(timeFormatter);
            LocalTime beginTime = LocalTime.parse(beginTimeStr);

            LocalDateTime planEndTime = planData.getPlan_trip_end_time();
            String endTimeStr = planEndTime.format(timeFormatter);
            LocalTime endTime = LocalTime.parse(endTimeStr);

            // 设置key值
            String keyData = routeCode+"&"+bus_id+"&"+plan_type;
            // 元组 1：开始时间， 2：结束时间，3：方向
            Tuple3<LocalTime,LocalTime,String> tuple = new Tuple3<>( beginTime, endTime ,direction);

            if(planTimeMap.containsKey(keyData)){
                List<Tuple3<LocalTime, LocalTime, String>> list = planTimeMap.get(keyData);
                list.add(tuple);
                planTimeMap.put(keyData, list);
            } else {
                List<Tuple3<LocalTime, LocalTime, String>> list = new ArrayList<>();
                list.add(tuple);
                planTimeMap.put(keyData, list);
            }
        }
        // 遍历集合 将 每辆车的 计划的时间排序
        for(String temp:planTimeMap.keySet()){
            List<Tuple3<LocalTime, LocalTime, String>> tuple3s = planTimeMap.get(temp);
            Collections.sort(tuple3s, Comparator.comparing(Tuple3::_1));
            planTimeMap.replace(temp,tuple3s);
        }
        return planTimeMap;
    }
    // 计算每天的 验证计划路单耗时 与 时段周转时间的是否冲突 abs（60 second）有效，异常给上 flag
    public void compute(){
        // 验证每天 每辆车的 发班执行
        HashMap<String, Integer> getFragment = GetFragment();
        HashMap<String, List<Tuple3<LocalTime, LocalTime, String>>> listHashMap = planSort();
        // 遍历计划表
        for(String temp:listHashMap.keySet()) {
            int index = 1;
            List<Tuple3<LocalTime, LocalTime, String>> tuple3s = listHashMap.get(temp);
            LocalTime beginTime = tuple3s.get(1)._1;
            LocalTime endTime = tuple3s.get(1)._2;
            // 计算计划 周转
            Duration duration = Duration.between(beginTime, endTime);
            // 获取计划 周转时间 （分钟）
            long planTimeMinute = duration.toMinutes() ;

            // 将周期值 进行分类 ：1-2-3-4-5
            String[] split = temp.split("&");
            String plan_type = split[split.length-1];
            // 遍历 周期时段周转时间
            for(String data :getFragment.keySet()){
                String[] split1 = data.split("&");
                String dayOfWeek = split1[split1.length - 1];
                // 根据计划验证 数据
                if(plan_type.contains(dayOfWeek)) {
                    String pDate = split1[0];
                    // 时段
                    String fragment = split1[3];
                    LocalTime fragmentTimeBegin = LocalTime.parse(fragment);
                    LocalTime fragmentTimeEnd = fragmentTimeBegin.plusMinutes(30);
                    // 周转时间
                    Integer tripTimeMinute = getFragment.get(data);

                    boolean isAfterOrEqualStartTime = !beginTime.isBefore(fragmentTimeBegin);
                    boolean isBeforeEndTime = beginTime.isBefore(fragmentTimeEnd);
                    // 判断 发车时间在 时段内
                    if (isAfterOrEqualStartTime && isBeforeEndTime) {

                        // 判断 计划周转 < 实际周转时间 6:00  7:00   50  60
                        //
                        if(planTimeMinute<tripTimeMinute){
                            System.out.println("日期："+pDate+","+temp+",第"+index+"班次->(发车："+beginTime+endTime+"到站："+planTimeMinute+")超时了"+tripTimeMinute);
                        }else {
                            System.out.println("按计划行事............................");
                            System.out.println("日期："+pDate+","+temp+",第"+index+"班次->(发车："+beginTime+endTime+"到站："+planTimeMinute+")没超时"+tripTimeMinute);
                        }
                    }
                }
            }
            index++;
        }

    }
    // 根据计划表周期 进行汇总，统计 是否有 连续三天 在相同时段出现异常的情况 标注计划 时间flag ，并统计 异常次数
}
