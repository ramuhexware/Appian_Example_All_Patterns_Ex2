package com.appian.enterprise.employee.rest.controller;

import com.appian.enterprise.employee.model.Employee;
import com.appian.enterprise.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Enterprise REST API Controller representing Appian Web API endpoints.
 * Appian Web APIs expose endpoints for external webhooks, mobile integrations, and systems.
 */
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeWebApiController {

    private final EmployeeService employeeService;

    public EmployeeWebApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") String id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body((Employee) Map.of("error", "Employee not found", "employeeId", id)));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee created = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        return employeeService.getEmployeeById(id)
                .map(existing -> {
                    employee.setEmployeeId(id);
                    Employee updated = employeeService.saveEmployee(employee);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable("id") String id) {
        boolean removed = employeeService.deleteEmployee(id);
        if (removed) {
            return ResponseEntity.ok(Map.of("message", "Employee deleted successfully", "employeeId", id, "status", "SUCCESS"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Employee not found", "employeeId", id, "status", "FAILED"));
        }
    }
}
