package com.example.demo.dao;


public class user_station {
    private Integer id;

    private String stationName;

    private String stationCode;

    public user_station(Integer id, String stationName, String stationCode) {
        this.id = id;
        this.stationName = stationName;
        this.stationCode = stationCode;
    }

    public user_station() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName == null ? null : stationName.trim();
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    @Override
    public String toString() {
        return "user_station{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", stationCode='" + stationCode + '\'' +
                '}';
    }
}