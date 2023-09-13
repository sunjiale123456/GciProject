package org.example.pojo;

public class ListData {
    private String dayType;
    private String busCode;
    private String firstTripDirection;
    private String[] planTripTime;

    public ListData() {
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }

    public String getFirstTripDirection() {
        return firstTripDirection;
    }

    public void setFirstTripDirection(String firstTripDirection) {
        this.firstTripDirection = firstTripDirection;
    }

    public String[] getPlanTripTime() {
        return planTripTime;
    }

    public void setPlanTripTime(String[] planTripTime) {
        this.planTripTime = planTripTime;
    }
}
