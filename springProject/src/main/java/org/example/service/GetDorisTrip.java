package org.example.service;
import lombok.Data;
import org.example.pojo.JsonData;
import org.example.pojo.TripData;
import org.example.utils.DorisConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Data
public class GetDorisTrip {
    public List<TripData> getResult(JsonData jsonData) {

        List<TripData> TripDataList = new ArrayList<>();
        String beginRange = jsonData.getBeginRange();
        String endRange = jsonData.getEndRange();
        try {
            Connection connection = DorisConnectionManager.getConnection(jsonData.getDataBase());
            String sql = "SELECT * FROM ods_dy_triplog WHERE `route_id_belong_to`='"+jsonData.getRouteCode()+"'"
                    +" AND pdate BETWEEN CAST('"+beginRange+"' as DATE ) AND CAST('"+endRange+"' as DATE )";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            // 读取路单表
            while (resultSet.next()) {
                // 处理查询结果
                TripData tripData = new TripData();
                tripData.setProvince_id(resultSet.getNString("province_id"));
                tripData.setProvince_name(resultSet.getNString("province_name"));
                tripData.setCity_id(resultSet.getNString("city_id"));
                tripData.setCity_name(resultSet.getNString("city_name"));
                tripData.setTo_station_id(resultSet.getNString("triplog_id"));
                tripData.setBus_id(resultSet.getNString("bus_id"));
                tripData.setChange_info(resultSet.getNString("change_info"));
                tripData.setDepart_interval(resultSet.getNString("depart_interval"));
                tripData.setDirection(resultSet.getNString("direction"));
                tripData.setEmployee_id(resultSet.getNString("employee_id"));
                tripData.setEmployee_name(resultSet.getNString("employee_name"));
                tripData.setFrom_station_id(resultSet.getNString("from_station_id"));
                tripData.setFrom_station_name(resultSet.getNString("from_station_name"));
                tripData.setNumber_plate(resultSet.getNString("number_plate"));
                tripData.setPlan_time(resultSet.getNString("plan_time"));
                tripData.setRecord_status(resultSet.getNString("record_status"));
                tripData.setRoute_id_belong_to(resultSet.getNString("route_id_belong_to"));
                tripData.setRoute_id_run(resultSet.getNString("route_id_run"));
                tripData.setRoute_run_code(resultSet.getNString("route_run_code"));
                tripData.setRoute_belongto_code(resultSet.getNString("route_belongto_code"));
                tripData.setRoute_name_belong_to(resultSet.getNString("route_name_belong_to"));
                tripData.setRoute_name_run(resultSet.getNString("route_name_run"));
                tripData.setRoute_sub_id(resultSet.getNString("route_sub_id"));
                tripData.setRoute_sub_name(resultSet.getNString("route_sub_name"));
                tripData.setRun_date(resultSet.getNString("run_date"));
                tripData.setRun_duration(resultSet.getNString("run_duration"));
                tripData.setRun_mileage(resultSet.getNString("run_mileage"));
                tripData.setRunning_no(resultSet.getNString("running_no"));
                tripData.setService_type(resultSet.getNString("service_type"));
                tripData.setTo_station_id(resultSet.getNString("to_station_id"));
                tripData.setFrom_station_name(resultSet.getNString("to_station_name"));
                tripData.setAuto_begin_time(resultSet.getNString("auto_begin_time"));
                tripData.setAuto_end_time(resultSet.getNString("auto_end_time"));
                tripData.setTriplog_begin_time(resultSet.getNString("triplog_begin_time"));
                tripData.setTriplog_end_time(resultSet.getNString("triplog_end_time"));
                tripData.setBus_code(resultSet.getNString("bus_code"));
                tripData.setQualification(resultSet.getNString("qualification"));
                tripData.setRoute_trips_type(resultSet.getNString("route_trips_type"));
                tripData.setOn_time_type(resultSet.getNString("on_time_type"));
                tripData.setRunning_log(resultSet.getNString("running_log"));
                tripData.setTrip_type(resultSet.getNString("trip_type"));
                tripData.setDispatch_time(resultSet.getNString("dispatch_time"));
                tripData.setDispatch_sent_time(resultSet.getNString("dispatch_sent_time"));
                tripData.setTrips(resultSet.getNString("trips"));
                tripData.setObuid(resultSet.getNString("obuid"));
                tripData.setOrgan_id(resultSet.getNString("organ_id"));
                tripData.setOrgan_name(resultSet.getNString("organ_name"));
                tripData.setBus_name(resultSet.getNString("bus_name"));
                tripData.setService_name(resultSet.getNString("service_name"));
                tripData.setGps_begin_time(resultSet.getNString("gps_begin_time"));
                tripData.setGps_mileage(resultSet.getNString("gps_mileage"));
                tripData.setRemark(resultSet.getNString("remark"));
                tripData.setPdate(resultSet.getNString("pdate"));
                //遍历结果
                TripDataList.add(tripData);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TripDataList;
    }
}
