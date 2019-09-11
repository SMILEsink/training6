package com.example.demo.dao;

import java.util.Date;

public class user_info {
    private Integer id;

    private String username;

    private String password;

    private String usercode;

    private String dpartmentCode;

    private String stationCode;

    private Date createTime;

    public user_info(Integer id, String username, String password, String usercode, String dpartmentCode, String stationCode, Date createTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.usercode = usercode;
        this.dpartmentCode = dpartmentCode;
        this.stationCode = stationCode;
        this.createTime = createTime;
    }

    public user_info() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getDpartmentCode() {
        return dpartmentCode;
    }

    public void setDpartmentCode(String dpartmentCode) {
        this.dpartmentCode = dpartmentCode == null ? null : dpartmentCode.trim();
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "user_info{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", usercode='" + usercode + '\'' +
                ", dpartmentCode='" + dpartmentCode + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}