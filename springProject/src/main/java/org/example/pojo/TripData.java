package org.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TripData {
    private String province_id ;
    private String province_name ;
    private String city_id ;
    private String city_name ;
    private String triplog_id ;
    private String bus_id ;
    private String change_info ;
    private String depart_interval ;
    private String direction ;
    private String employee_id ;
    private String employee_name ;
    private String from_station_id ;
    private String from_station_name ;
    private String number_plate ;
    private String plan_time ;
    private String record_status ;
    private String route_id_belong_to ;
    private String route_id_run ;
    private String route_run_code ;
    private String route_belongto_code ;
    private String route_name_belong_to ;
    private String route_name_run ;
    private String route_sub_id ;
    private String route_sub_name ;
    private String run_date ;
    private String run_duration ;
    private String run_mileage ;
    private String running_no ;
    private String service_type ;
    private String to_station_id ;
    private String to_station_name ;
    private String auto_begin_time ;
    private String auto_end_time ;
    private String triplog_begin_time ;
    private String triplog_end_time ;
    private String bus_code ;
    private String qualification ;
    private String route_trips_type ;
    private String on_time_type ;
    private String running_log ;
    private String trip_type ;
    private String dispatch_time ;
    private String dispatch_sent_time ;
    private String trips ;
    private String obuid ;
    private String organ_id ;
    private String organ_name ;
    private String bus_name ;
    private String service_name ;
    private String gps_begin_time ;
    private String gps_mileage ;
    private String remark ;
    private String pdate ;

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getTriplog_id() {
        return triplog_id;
    }

    public void setTriplog_id(String triplog_id) {
        this.triplog_id = triplog_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getChange_info() {
        return change_info;
    }

    public void setChange_info(String change_info) {
        this.change_info = change_info;
    }

    public String getDepart_interval() {
        return depart_interval;
    }

    public void setDepart_interval(String depart_interval) {
        this.depart_interval = depart_interval;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getFrom_station_id() {
        return from_station_id;
    }

    public void setFrom_station_id(String from_station_id) {
        this.from_station_id = from_station_id;
    }

    public String getFrom_station_name() {
        return from_station_name;
    }

    public void setFrom_station_name(String from_station_name) {
        this.from_station_name = from_station_name;
    }

    public String getNumber_plate() {
        return number_plate;
    }

    public void setNumber_plate(String number_plate) {
        this.number_plate = number_plate;
    }

    public String getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(String plan_time) {
        this.plan_time = plan_time;
    }

    public String getRecord_status() {
        return record_status;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

    public String getRoute_id_belong_to() {
        return route_id_belong_to;
    }

    public void setRoute_id_belong_to(String route_id_belong_to) {
        this.route_id_belong_to = route_id_belong_to;
    }

    public String getRoute_id_run() {
        return route_id_run;
    }

    public void setRoute_id_run(String route_id_run) {
        this.route_id_run = route_id_run;
    }

    public String getRoute_run_code() {
        return route_run_code;
    }

    public void setRoute_run_code(String route_run_code) {
        this.route_run_code = route_run_code;
    }

    public String getRoute_belongto_code() {
        return route_belongto_code;
    }

    public void setRoute_belongto_code(String route_belongto_code) {
        this.route_belongto_code = route_belongto_code;
    }

    public String getRoute_name_belong_to() {
        return route_name_belong_to;
    }

    public void setRoute_name_belong_to(String route_name_belong_to) {
        this.route_name_belong_to = route_name_belong_to;
    }

    public String getRoute_name_run() {
        return route_name_run;
    }

    public void setRoute_name_run(String route_name_run) {
        this.route_name_run = route_name_run;
    }

    public String getRoute_sub_id() {
        return route_sub_id;
    }

    public void setRoute_sub_id(String route_sub_id) {
        this.route_sub_id = route_sub_id;
    }

    public String getRoute_sub_name() {
        return route_sub_name;
    }

    public void setRoute_sub_name(String route_sub_name) {
        this.route_sub_name = route_sub_name;
    }

    public String getRun_date() {
        return run_date;
    }

    public void setRun_date(String run_date) {
        this.run_date = run_date;
    }

    public String getRun_duration() {
        return run_duration;
    }

    public void setRun_duration(String run_duration) {
        this.run_duration = run_duration;
    }

    public String getRun_mileage() {
        return run_mileage;
    }

    public void setRun_mileage(String run_mileage) {
        this.run_mileage = run_mileage;
    }

    public String getRunning_no() {
        return running_no;
    }

    public void setRunning_no(String running_no) {
        this.running_no = running_no;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getTo_station_id() {
        return to_station_id;
    }

    public void setTo_station_id(String to_station_id) {
        this.to_station_id = to_station_id;
    }

    public String getTo_station_name() {
        return to_station_name;
    }

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    public String getAuto_begin_time() {
        return auto_begin_time;
    }

    public void setAuto_begin_time(String auto_begin_time) {
        this.auto_begin_time = auto_begin_time;
    }

    public String getAuto_end_time() {
        return auto_end_time;
    }

    public void setAuto_end_time(String auto_end_time) {
        this.auto_end_time = auto_end_time;
    }

    public String getTriplog_begin_time() {
        return triplog_begin_time;
    }

    public void setTriplog_begin_time(String triplog_begin_time) {
        this.triplog_begin_time = triplog_begin_time;
    }

    public String getTriplog_end_time() {
        return triplog_end_time;
    }

    public void setTriplog_end_time(String triplog_end_time) {
        this.triplog_end_time = triplog_end_time;
    }

    public String getBus_code() {
        return bus_code;
    }

    public void setBus_code(String bus_code) {
        this.bus_code = bus_code;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getRoute_trips_type() {
        return route_trips_type;
    }

    public void setRoute_trips_type(String route_trips_type) {
        this.route_trips_type = route_trips_type;
    }

    public String getOn_time_type() {
        return on_time_type;
    }

    public void setOn_time_type(String on_time_type) {
        this.on_time_type = on_time_type;
    }

    public String getRunning_log() {
        return running_log;
    }

    public void setRunning_log(String running_log) {
        this.running_log = running_log;
    }

    public String getTrip_type() {
        return trip_type;
    }

    public void setTrip_type(String trip_type) {
        this.trip_type = trip_type;
    }

    public String getDispatch_time() {
        return dispatch_time;
    }

    public void setDispatch_time(String dispatch_time) {
        this.dispatch_time = dispatch_time;
    }

    public String getDispatch_sent_time() {
        return dispatch_sent_time;
    }

    public void setDispatch_sent_time(String dispatch_sent_time) {
        this.dispatch_sent_time = dispatch_sent_time;
    }

    public String getTrips() {
        return trips;
    }

    public void setTrips(String trips) {
        this.trips = trips;
    }

    public String getObuid() {
        return obuid;
    }

    public void setObuid(String obuid) {
        this.obuid = obuid;
    }

    public String getOrgan_id() {
        return organ_id;
    }

    public void setOrgan_id(String organ_id) {
        this.organ_id = organ_id;
    }

    public String getOrgan_name() {
        return organ_name;
    }

    public void setOrgan_name(String organ_name) {
        this.organ_name = organ_name;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getGps_begin_time() {
        return gps_begin_time;
    }

    public void setGps_begin_time(String gps_begin_time) {
        this.gps_begin_time = gps_begin_time;
    }

    public String getGps_mileage() {
        return gps_mileage;
    }

    public void setGps_mileage(String gps_mileage) {
        this.gps_mileage = gps_mileage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }
}
