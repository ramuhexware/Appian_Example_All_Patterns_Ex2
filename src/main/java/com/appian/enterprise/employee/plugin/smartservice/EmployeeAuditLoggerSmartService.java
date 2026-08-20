package com.appian.enterprise.employee.plugin.smartservice;

import com.appian.enterprise.employee.plugin.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom Appian Smart Service Component for Process Models.
 * Executed as an automated activity step within an Appian Process Flow.
 */
@SmartService
@Name("Employee Audit Logger Smart Service")
@Description("Logs enterprise employee modification events to audit system from Appian process workflows")
public class EmployeeAuditLoggerSmartService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeAuditLoggerSmartService.class);

    @Input(required = true)
    @Name("EmployeeID")
    @Description("Unique Identifier of Employee")
    private String employeeId;

    @Input(required = true)
    @Name("Action")
    @Description("Action performed (e.g. CREATE, UPDATE, TERMINATE)")
    private String action;

    @Input
    @Name("UpdatedBy")
    @Description("User ID initiating the change")
    private String updatedBy;

    @Output
    @Name("Success")
    @Description("Status indicating audit log success")
    private boolean success;

    @Output
    @Name("AuditLogId")
    @Description("Generated Audit Log Identifier")
    private String auditLogId;

    public void run() {
        LOG.info("Executing Appian Smart Service [EmployeeAuditLoggerSmartService]");
        LOG.info("Audit Entry -> Employee: {}, Action: {}, User: {}", employeeId, action, updatedBy);

        this.auditLogId = "AUDIT-" + System.currentTimeMillis();
        this.success = true;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getAuditLogId() {
        return auditLogId;
    }
}
