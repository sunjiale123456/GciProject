package org.example.pojo;
import lombok.Data;

@Data
public class JsonData {
    private String cityId;
    private String dataBase;
    private String routeCode;
    private String intelligentId;
    private String beginRange ;
    private String endRange ;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getIntelligentId() {
        return intelligentId;
    }

    public void setIntelligentId(String intelligentId) {
        this.intelligentId = intelligentId;
    }

    public String getBeginRange() {
        return beginRange;
    }

    public void setBeginRange(String beginRange) {
        this.beginRange = beginRange;
    }

    public String getEndRange() {
        return endRange;
    }

    public void setEndRange(String endRange) {
        this.endRange = endRange;
    }
}

