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

import java.util.Optional;

/**
 * Enterprise SOAP Web Service Endpoint representing Appian SOAP Web Service integration.
 * Appian process models consume or expose WSDL-based SOAP web services for legacy enterprise systems.
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

    @WebMethod(operationName = "getEmployeeDetailsSOAP")
    @WebResult(name = "SoapEmployeeResponse")
    public SoapEmployeeResponse getEmployeeDetailsSOAP(
            @WebParam(name = "SoapEmployeeRequest") SoapEmployeeRequest request) {

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
}
