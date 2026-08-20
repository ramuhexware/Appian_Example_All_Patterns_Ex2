package com.appian.enterprise.employee.model;

import java.io.Serializable;

public class Department implements Serializable {
    private String departmentId;
    private String name;
    private String code;
    private String managerName;

    public Department() {}

    public Department(String departmentId, String name, String code, String managerName) {
        this.departmentId = departmentId;
        this.name = name;
        this.code = code;
        this.managerName = managerName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
