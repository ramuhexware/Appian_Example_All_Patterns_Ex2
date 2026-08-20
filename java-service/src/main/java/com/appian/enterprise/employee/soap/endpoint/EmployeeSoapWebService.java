package com.appian.enterprise.employee.soap.endpoint;

import com.appian.enterprise.employee.model.Employee;
import com.appian.enterprise.employee.service.EmployeeService;
import com.appian.enterprise.employee.soap.model.SoapEmployeeRequest;
import com.appian.enterprise.employee.soap.model.SoapEmployeeResponse;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Enterprise SOAP Web Service Endpoint representing Appian SOAP Web Service integration.
 * Appian process models consume or expose WSDL-based SOAP web services for legacy enterprise systems.
 * Serves operations: getEmployeeDetails, evaluateEmployee, getEmployeeDetailsSOAP, evaluateEmployeeSOAP.
 */
@Component
@WebService(
        serviceName = "EmployeeSoapWebService",
        portName = "EmployeeSoapPort",
        targetNamespace = "http://soap.employee.enterprise.appian.com/"
)
public class EmployeeSoapWebService {

    private final EmployeeService employeeService;

    public EmployeeSoapWebService() {
        this.employeeService = new EmployeeService();
    }

    @WebMethod(operationName = "getEmployeeDetails")
    @WebResult(name = "SoapEmployeeResponse")
    public SoapEmployeeResponse getEmployeeDetails(
            @WebParam(name = "SoapEmployeeRequest") SoapEmployeeRequest request) {
        return handleGetDetails(request);
    }

    @WebMethod(operationName = "getEmployeeDetailsSOAP")
    @WebResult(name = "SoapEmployeeResponse")
    public SoapEmployeeResponse getEmployeeDetailsSOAP(
            @WebParam(name = "SoapEmployeeRequest") SoapEmployeeRequest request) {
        return handleGetDetails(request);
    }

    @WebMethod(operationName = "evaluateEmployee")
    @WebResult(name = "SoapEmployeeResponse")
    public SoapEmployeeResponse evaluateEmployee(
            @WebParam(name = "SoapEmployeeRequest") SoapEmployeeRequest request) {
        return handleEvaluate(request);
    }

    @WebMethod(operationName = "evaluateEmployeeSOAP")
    @WebResult(name = "SoapEmployeeResponse")
    public SoapEmployeeResponse evaluateEmployeeSOAP(
            @WebParam(name = "SoapEmployeeRequest") SoapEmployeeRequest request) {
        return handleEvaluate(request);
    }

    private SoapEmployeeResponse handleGetDetails(SoapEmployeeRequest request) {
        SoapEmployeeResponse response = new SoapEmployeeResponse();
        if (request == null || request.getEmployeeId() == null) {
            response.setResponseCode("INVALID_REQUEST");
            return response;
        }

        Optional<Employee> empOpt = employeeService.getEmployeeById(request.getEmployeeId());
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            response.setEmployeeId(emp.getEmployeeId());
            response.setFullName(emp.getFirstName() + " " + emp.getLastName());
            response.setEmail(emp.getEmail());
            response.setTitle(emp.getTitle());
            response.setSalary(emp.getSalary());
            response.setStatus(emp.getStatus());
            response.setResponseCode("SUCCESS_200");
        } else {
            response.setEmployeeId(request.getEmployeeId());
            response.setResponseCode("NOT_FOUND_404");
        }

        return response;
    }

    private SoapEmployeeResponse handleEvaluate(SoapEmployeeRequest request) {
        SoapEmployeeResponse response = new SoapEmployeeResponse();
        if (request == null) {
            response.setResponseCode("INVALID_REQUEST");
            return response;
        }

        Employee emp = new Employee();
        emp.setFirstName(request.getFirstName() != null ? request.getFirstName() : "Appian");
        emp.setLastName(request.getLastName() != null ? request.getLastName() : "User");
        emp.setEmail(request.getEmail() != null ? request.getEmail() : "user@enterprise.com");
        emp.setDepartment(request.getDepartment() != null ? request.getDepartment() : "IT");
        emp.setSalary(request.getSalary() != null ? request.getSalary() : BigDecimal.valueOf(95000));
        emp.setStatus("EVALUATED_SOAP");

        Employee saved = employeeService.createEmployee(emp);
        response.setEmployeeId(saved.getEmployeeId());
        response.setFullName(saved.getFirstName() + " " + saved.getLastName());
        response.setEmail(saved.getEmail());
        response.setTitle("Software Engineer");
        response.setSalary(saved.getSalary());
        response.setStatus(saved.getStatus());
        response.setResponseCode("SOAP_EVALUATION_SUCCESS");

        return response;
    }
}
