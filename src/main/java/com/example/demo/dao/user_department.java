package com.example.demo.dao;


public class user_department {
    private Integer id;

    private String departmentCode;

    private String departmentName;

    public user_department(Integer id, String departmentCode, String departmentName) {
        this.id = id;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    public user_department() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode == null ? null : departmentCode.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    @Override
    public String toString() {
        return "user_department{" +
                "id=" + id +
                ", departmentCode='" + departmentCode + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}