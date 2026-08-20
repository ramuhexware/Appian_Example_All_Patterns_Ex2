package com.appian.enterprise.employee;

import com.appian.enterprise.employee.model.Employee;
import com.appian.enterprise.employee.plugin.function.EmployeeExpressionFunctions;
import com.appian.enterprise.employee.plugin.smartservice.EmployeeAuditLoggerSmartService;
import com.appian.enterprise.employee.plugin.smartservice.EmployeeBonusCalculatorSmartService;
import com.appian.enterprise.employee.service.EmployeeService;
import com.appian.enterprise.employee.soap.endpoint.EmployeeSoapWebService;
import com.appian.enterprise.employee.soap.model.SoapEmployeeRequest;
import com.appian.enterprise.employee.soap.model.SoapEmployeeResponse;
import com.appian.enterprise.employee.struts.EmployeeStrutsAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppianEmployeeApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testEmployeeServiceOperations() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertFalse(employees.isEmpty(), "Employee list should not be empty");

        Employee newEmp = new Employee();
        newEmp.setFirstName("Test");
        newEmp.setLastName("User");
        newEmp.setEmail("test.user@enterprise.com");
        newEmp.setSalary(new BigDecimal("85000"));

        Employee saved = employeeService.saveEmployee(newEmp);
        assertNotNull(saved.getEmployeeId());
        assertEquals("ACTIVE", saved.getStatus());
    }

    @Test
    void testAppianPlugInSmartService() {
        EmployeeAuditLoggerSmartService auditLogger = new EmployeeAuditLoggerSmartService();
        auditLogger.setEmployeeId("EMP-1001");
        auditLogger.setAction("UPDATE");
        auditLogger.setUpdatedBy("ProcessUserAdmin");
        auditLogger.run();

        assertTrue(auditLogger.isSuccess());
        assertNotNull(auditLogger.getAuditLogId());
    }

    @Test
    void testAppianExpressionFunctions() {
        BigDecimal bonus = EmployeeExpressionFunctions.calculateEmployeeBonus(100000.0, 1.5);
        assertEquals(new BigDecimal("15000.0"), bonus);

        assertTrue(EmployeeExpressionFunctions.validateEmployeeSSN("123-45-6789"));
        assertFalse(EmployeeExpressionFunctions.validateEmployeeSSN("INVALID-SSN"));
    }

    @Test
    void testApacheStrutsAction() {
        EmployeeStrutsAction action = new EmployeeStrutsAction();
        String result = action.execute();
        assertEquals("success", result);
        assertFalse(action.getEmployeeList().isEmpty());
    }

    @Test
    void testSOAPWebServiceEndpoint() {
        EmployeeSoapWebService soapService = new EmployeeSoapWebService();
        SoapEmployeeRequest request = new SoapEmployeeRequest("EMP-1001", "UnitTest");
        SoapEmployeeResponse response = soapService.getEmployeeDetailsSOAP(request);

        assertEquals("SUCCESS_200", response.getResponseCode());
        assertEquals("EMP-1001", response.getEmployeeId());
    }
}
