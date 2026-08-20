package com.appian.enterprise.employee.servlet;

import com.appian.enterprise.employee.model.Employee;
import com.appian.enterprise.employee.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Enterprise Java Web Servlet representing legacy/custom Java Web Servlet integration pattern.
 * Serves HTTP GET requests for dynamically exporting employee data into CSV/Text format.
 */
@Component
@WebServlet(name = "EmployeeExportServlet", urlPatterns = "/servlet/export-employees")
public class EmployeeExportServlet extends HttpServlet {

    private final EmployeeService employeeService;

    public EmployeeExportServlet() {
        this.employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/csv");
        resp.setHeader("Content-Disposition", "attachment; filename=\"employee_export.csv\"");

        PrintWriter writer = resp.getWriter();
        writer.println("EmployeeID,FirstName,LastName,Email,Title,DepartmentID,Salary,Status,HireDate");

        List<Employee> employees = employeeService.getAllEmployees();
        for (Employee emp : employees) {
            writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                    emp.getEmployeeId(),
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getEmail(),
                    emp.getTitle(),
                    emp.getDepartmentId(),
                    emp.getSalary(),
                    emp.getStatus(),
                    emp.getHireDate());
        }
        writer.flush();
    }
}
