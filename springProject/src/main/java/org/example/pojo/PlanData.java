package org.example.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
@Data
public class PlanData{
        private String id ;
        private String task_id ;
        private String route_id ;
        private String route_code ;
        private String route_name ;
        private Date plan_date ;
        private LocalDateTime plan_trip_begin_time ; //DateTime
        private String plan_type ;
        private LocalDateTime plan_trip_end_time ; //DateTime
        private String service_type ;
        private String service_name ;
        private String direction ;
        private String bus_id ;
        private String number_plate ;
        private String bus_name ;
        private String bus_code ;
        private String obu_id ;
        private String first_route_sta_id ;
        private String first_route_station_name ;
        private String last_route_sta_id ;
        private String last_route_station_name ;
        private String first_station_id ;
        private String first_station_name ;
        private String last_station_id ;
        private String last_station_name ;
        private String gps_num ;
        private String employee_id ;
        private String employee_name ;
        private String employee_code ;
        private String service_code ;
        private LocalDateTime create_time ; //DateTime
        private String create_by ;
        private LocalDateTime update_time ; //DateTime
        private String update_by ;
        private Integer plan_status ;
        private Integer trip_num ;
        private String plan_version ;
        private String city_id ;
        private String city_name ;
        private String province_id ;
        private String province_name ;
        private String organ_name ;
        private String organ_id ;
        private Integer tenant_id ;
        private String intelligent_id ;
        private Integer sort;
        private LocalDateTime pdate;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getTask_id() {
                return task_id;
        }

        public void setTask_id(String task_id) {
                this.task_id = task_id;
        }

        public String getRoute_id() {
                return route_id;
        }

        public void setRoute_id(String route_id) {
                this.route_id = route_id;
        }

        public String getRoute_code() {
                return route_code;
        }

        public void setRoute_code(String route_code) {
                this.route_code = route_code;
        }

        public String getRoute_name() {
                return route_name;
        }

        public void setRoute_name(String route_name) {
                this.route_name = route_name;
        }

        public Date getPlan_date() {
                return plan_date;
        }

        public void setPlan_date(Date plan_date) {
                this.plan_date = plan_date;
        }

        public LocalDateTime getPlan_trip_begin_time() {
                return plan_trip_begin_time;
        }

        public void setPlan_trip_begin_time(LocalDateTime plan_trip_begin_time) {
                this.plan_trip_begin_time = plan_trip_begin_time;
        }

        public String getPlan_type() {
                return plan_type;
        }

        public void setPlan_type(String plan_type) {
                this.plan_type = plan_type;
        }

        public LocalDateTime getPlan_trip_end_time() {
                return plan_trip_end_time;
        }

        public void setPlan_trip_end_time(LocalDateTime plan_trip_end_time) {
                this.plan_trip_end_time = plan_trip_end_time;
        }

        public String getService_type() {
                return service_type;
        }

        public void setService_type(String service_type) {
                this.service_type = service_type;
        }

        public String getService_name() {
                return service_name;
        }

        public void setService_name(String service_name) {
                this.service_name = service_name;
        }

        public String getDirection() {
                return direction;
        }

        public void setDirection(String direction) {
                this.direction = direction;
        }

        public String getBus_id() {
                return bus_id;
        }

        public void setBus_id(String bus_id) {
                this.bus_id = bus_id;
        }

        public String getNumber_plate() {
                return number_plate;
        }

        public void setNumber_plate(String number_plate) {
                this.number_plate = number_plate;
        }

        public String getBus_name() {
                return bus_name;
        }

        public void setBus_name(String bus_name) {
                this.bus_name = bus_name;
        }

        public String getBus_code() {
                return bus_code;
        }

        public void setBus_code(String bus_code) {
                this.bus_code = bus_code;
        }

        public String getObu_id() {
                return obu_id;
        }

        public void setObu_id(String obu_id) {
                this.obu_id = obu_id;
        }

        public String getFirst_route_sta_id() {
                return first_route_sta_id;
        }

        public void setFirst_route_sta_id(String first_route_sta_id) {
                this.first_route_sta_id = first_route_sta_id;
        }

        public String getFirst_route_station_name() {
                return first_route_station_name;
        }

        public void setFirst_route_station_name(String first_route_station_name) {
                this.first_route_station_name = first_route_station_name;
        }

        public String getLast_route_sta_id() {
                return last_route_sta_id;
        }

        public void setLast_route_sta_id(String last_route_sta_id) {
                this.last_route_sta_id = last_route_sta_id;
        }

        public String getLast_route_station_name() {
                return last_route_station_name;
        }

        public void setLast_route_station_name(String last_route_station_name) {
                this.last_route_station_name = last_route_station_name;
        }

        public String getFirst_station_id() {
                return first_station_id;
        }

        public void setFirst_station_id(String first_station_id) {
                this.first_station_id = first_station_id;
        }

        public String getFirst_station_name() {
                return first_station_name;
        }

        public void setFirst_station_name(String first_station_name) {
                this.first_station_name = first_station_name;
        }

        public String getLast_station_id() {
                return last_station_id;
        }

        public void setLast_station_id(String last_station_id) {
                this.last_station_id = last_station_id;
        }

        public String getLast_station_name() {
                return last_station_name;
        }

        public void setLast_station_name(String last_station_name) {
                this.last_station_name = last_station_name;
        }

        public String getGps_num() {
                return gps_num;
        }

        public void setGps_num(String gps_num) {
                this.gps_num = gps_num;
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

        public String getEmployee_code() {
                return employee_code;
        }

        public void setEmployee_code(String employee_code) {
                this.employee_code = employee_code;
        }

        public String getService_code() {
                return service_code;
        }

        public void setService_code(String service_code) {
                this.service_code = service_code;
        }

        public LocalDateTime getCreate_time() {
                return create_time;
        }

        public void setCreate_time(LocalDateTime create_time) {
                this.create_time = create_time;
        }

        public String getCreate_by() {
                return create_by;
        }

        public void setCreate_by(String create_by) {
                this.create_by = create_by;
        }

        public LocalDateTime getUpdate_time() {
                return update_time;
        }

        public void setUpdate_time(LocalDateTime update_time) {
                this.update_time = update_time;
        }

        public String getUpdate_by() {
                return update_by;
        }

        public void setUpdate_by(String update_by) {
                this.update_by = update_by;
        }

        public Integer getPlan_status() {
                return plan_status;
        }

        public void setPlan_status(Integer plan_status) {
                this.plan_status = plan_status;
        }

        public Integer getTrip_num() {
                return trip_num;
        }

        public void setTrip_num(Integer trip_num) {
                this.trip_num = trip_num;
        }

        public String getPlan_version() {
                return plan_version;
        }

        public void setPlan_version(String plan_version) {
                this.plan_version = plan_version;
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

        public String getOrgan_name() {
                return organ_name;
        }

        public void setOrgan_name(String organ_name) {
                this.organ_name = organ_name;
        }

        public String getOrgan_id() {
                return organ_id;
        }

        public void setOrgan_id(String organ_id) {
                this.organ_id = organ_id;
        }

        public Integer getTenant_id() {
                return tenant_id;
        }

        public void setTenant_id(Integer tenant_id) {
                this.tenant_id = tenant_id;
        }

        public String getIntelligent_id() {
                return intelligent_id;
        }

        public void setIntelligent_id(String intelligent_id) {
                this.intelligent_id = intelligent_id;
        }

        public Integer getSort() {
                return sort;
        }

        public void setSort(Integer sort) {
                this.sort = sort;
        }

        public LocalDateTime getPdate() {
                return pdate;
        }

        public void setPdate(LocalDateTime pdate) {
                this.pdate = pdate;
        }
}