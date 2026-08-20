package com.appian.enterprise.employee.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String departmentId;
    private BigDecimal salary;
    private String status; // ACTIVE, PENDING, TERMINATED
    private LocalDate hireDate;

    public Employee() {}

    public Employee(String employeeId, String firstName, String lastName, String email, String title, String departmentId, BigDecimal salary, String status, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.departmentId = departmentId;
        this.salary = salary;
        this.status = status;
        this.hireDate = hireDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    // Alias for Appian CDT property matching
    public String getDepartment() {
        return departmentId;
    }

    public void setDepartment(String department) {
        this.departmentId = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", salary=" + salary +
                ", status='" + status + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
