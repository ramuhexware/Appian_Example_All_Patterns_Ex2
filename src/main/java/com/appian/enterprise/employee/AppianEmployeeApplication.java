package com.appian.enterprise.employee;

import com.appian.enterprise.employee.soap.endpoint.EmployeeSoapWebService;
import jakarta.xml.ws.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.appian.enterprise.employee.servlet")
@EnableJms
public class AppianEmployeeApplication {

    private static final Logger LOG = LoggerFactory.getLogger(AppianEmployeeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AppianEmployeeApplication.class, args);
    }

    @Bean
    public CommandLineRunner initSoapEndpoint(EmployeeSoapWebService soapWebService) {
        return args -> {
            try {
                String soapUrl = "http://localhost:8088/ws/employees";
                Endpoint.publish(soapUrl, soapWebService);
                LOG.info("=== [SOAP Web Service] Endpoint published at: {} ===", soapUrl);
                LOG.info("=== [SOAP Web Service] WSDL Contract available at: {}?wsdl ===", soapUrl);
            } catch (Exception e) {
                LOG.warn("SOAP Endpoint publishing notice: {}", e.getMessage());
            }
        };
    }
}
