package com.appian.enterprise.employee.rest.client;

import com.appian.enterprise.employee.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Enterprise REST Client representing Appian Outbound Integration & Connected System pattern.
 * Used by Appian process models to call external HTTP REST APIs.
 */
@Component
public class EmployeeExternalRestClient {

    private final RestTemplate restTemplate;

    public EmployeeExternalRestClient() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> fetchExternalBackgroundCheck(String employeeId) {
        // Simulates outbound REST call via Appian Connected System HTTP integration
        return Map.of(
                "employeeId", employeeId,
                "backgroundStatus", "PASSED",
                "clearanceLevel", "CONFIDENTIAL",
                "verifiedDate", "2026-08-20",
                "sourceSystem", "External-HR-BackgroundCheck-Service"
        );
    }
}
