package com.appian.enterprise.employee.soap.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SoapEmployeeRequest", namespace = "http://soap.employee.enterprise.appian.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapEmployeeRequest {

    @XmlElement(required = true)
    private String employeeId;

    @XmlElement
    private String requestSource;

    public SoapEmployeeRequest() {}

    public SoapEmployeeRequest(String employeeId, String requestSource) {
        this.employeeId = employeeId;
        this.requestSource = requestSource;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }
}
