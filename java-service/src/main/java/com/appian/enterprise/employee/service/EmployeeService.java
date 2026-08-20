package com.appian.enterprise.employee.service;

import com.appian.enterprise.employee.model.Department;
import com.appian.enterprise.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {

    private final Map<String, Employee> employeeStore = new ConcurrentHashMap<>();
    private final Map<String, Department> departmentStore = new ConcurrentHashMap<>();

    public EmployeeService() {
        // Initialize Sample Enterprise Employee Data
        Department hr = new Department("DEP-101", "Human Resources", "HR", "Sarah Jenkins");
        Department eng = new Department("DEP-102", "Software Engineering", "ENG", "David Chen");
        Department fin = new Department("DEP-103", "Finance & Operations", "FIN", "Maria Rodriguez");

        departmentStore.put(hr.getDepartmentId(), hr);
        departmentStore.put(eng.getDepartmentId(), eng);
        departmentStore.put(fin.getDepartmentId(), fin);

        saveEmployee(new Employee("EMP-1001", "John", "Doe", "john.doe@enterprise.com", "Senior Software Engineer", "DEP-102", new BigDecimal("135000"), "ACTIVE", LocalDate.of(2021, 3, 15)));
        saveEmployee(new Employee("EMP-1002", "Alice", "Smith", "alice.smith@enterprise.com", "HR Specialist", "DEP-101", new BigDecimal("75000"), "ACTIVE", LocalDate.of(2022, 6, 1)));
        saveEmployee(new Employee("EMP-1003", "Robert", "Johnson", "robert.johnson@enterprise.com", "Financial Analyst", "DEP-103", new BigDecimal("92000"), "ACTIVE", LocalDate.of(2020, 11, 10)));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeStore.values());
    }

    public Optional<Employee> getEmployeeById(String employeeId) {
        return Optional.ofNullable(employeeStore.get(employeeId));
    }

    public Employee saveEmployee(Employee employee) {
        if (employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()) {
            employee.setEmployeeId("EMP-" + (1000 + employeeStore.size() + 1));
        }
        if (employee.getStatus() == null) {
            employee.setStatus("ACTIVE");
        }
        employeeStore.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        return saveEmployee(employee);
    }

    public boolean deleteEmployee(String employeeId) {
        return employeeStore.remove(employeeId) != null;
    }

    public List<Department> getAllDepartments() {
        return new ArrayList<>(departmentStore.values());
    }

    public BigDecimal calculateBonus(Employee employee, double performanceMultiplier) {
        if (employee == null || employee.getSalary() == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal baseBonus = employee.getSalary().multiply(new BigDecimal("0.10"));
        return baseBonus.multiply(new BigDecimal(String.valueOf(performanceMultiplier)));
    }
}
