package com.appian.enterprise.employee.struts;

import com.appian.enterprise.employee.model.Employee;
import com.appian.enterprise.employee.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Apache Struts 2 Action class handling web form actions and view responses.
 */
public class EmployeeStrutsAction extends ActionSupport {

    private final EmployeeService employeeService = new EmployeeService();

    private List<Employee> employeeList;
    private Employee employee = new Employee();
    private String employeeId;
    private String message;

    // Struts Action Method: List all employees
    public String execute() {
        this.employeeList = employeeService.getAllEmployees();
        this.message = "Successfully loaded " + employeeList.size() + " employees via Apache Struts 2 Action";
        return SUCCESS;
    }

    // Struts Action Method: Save or update employee
    public String saveEmployee() {
        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            addActionError("First Name is required!");
            return INPUT;
        }

        if (employee.getSalary() == null) {
            employee.setSalary(new BigDecimal("60000"));
        }
        if (employee.getHireDate() == null) {
            employee.setHireDate(LocalDate.now());
        }

        Employee saved = employeeService.saveEmployee(employee);
        this.message = "Employee " + saved.getFirstName() + " " + saved.getLastName() + " (" + saved.getEmployeeId() + ") saved via Struts 2 Action!";
        this.employeeList = employeeService.getAllEmployees();
        return SUCCESS;
    }

    // Struts Action Method: Delete employee
    public String deleteEmployee() {
        if (employeeId != null && !employeeId.isEmpty()) {
            boolean removed = employeeService.deleteEmployee(employeeId);
            if (removed) {
                this.message = "Deleted Employee ID: " + employeeId + " via Struts 2 Action";
            } else {
                addActionError("Employee ID not found: " + employeeId);
            }
        }
        this.employeeList = employeeService.getAllEmployees();
        return SUCCESS;
    }

    // Getters and Setters
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
