package com.appian.enterprise.employee.soap.controller;

import com.appian.enterprise.employee.soap.endpoint.EmployeeSoapWebService;
import com.appian.enterprise.employee.soap.model.SoapEmployeeRequest;
import com.appian.enterprise.employee.soap.model.SoapEmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Spring Web Controller exposing HTTP SOAP endpoints at /ws/employees and /ws/soap.
 * Provides direct HTTP POST SOAP XML processing and WSDL discovery for Appian Integration objects.
 */
@RestController
@RequestMapping({"/ws/employees", "/ws/soap"})
public class EmployeeSoapHttpController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeSoapHttpController.class);
    private final EmployeeSoapWebService soapWebService;

    public EmployeeSoapHttpController(EmployeeSoapWebService soapWebService) {
        this.soapWebService = soapWebService;
    }

    /**
     * WSDL Endpoint discovery for Appian SOAP Web Service integrations.
     */
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getWsdl(@RequestParam(value = "wsdl", required = false) String wsdl) {
        String wsdlXml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                                  xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                                  xmlns:tns="http://soap.employee.enterprise.appian.com/"
                                  targetNamespace="http://soap.employee.enterprise.appian.com/"
                                  name="EmployeeSoapWebService">
                    <wsdl:types>
                        <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" targetNamespace="http://soap.employee.enterprise.appian.com/">
                            <xs:element name="getEmployeeDetailsRequest">
                                <xs:complexType>
                                    <xs:sequence>
                                        <xs:element name="employeeId" type="xs:string"/>
                                    </xs:sequence>
                                </xs:complexType>
                            </xs:element>
                            <xs:element name="evaluateEmployeeRequest">
                                <xs:complexType>
                                    <xs:sequence>
                                        <xs:element name="firstName" type="xs:string"/>
                                        <xs:element name="lastName" type="xs:string"/>
                                        <xs:element name="email" type="xs:string"/>
                                        <xs:element name="department" type="xs:string"/>
                                        <xs:element name="salary" type="xs:decimal"/>
                                    </xs:sequence>
                                </xs:complexType>
                            </xs:element>
                            <xs:element name="SoapEmployeeResponse">
                                <xs:complexType>
                                    <xs:sequence>
                                        <xs:element name="employeeId" type="xs:string"/>
                                        <xs:element name="fullName" type="xs:string"/>
                                        <xs:element name="email" type="xs:string"/>
                                        <xs:element name="title" type="xs:string"/>
                                        <xs:element name="salary" type="xs:decimal"/>
                                        <xs:element name="status" type="xs:string"/>
                                        <xs:element name="responseCode" type="xs:string"/>
                                    </xs:sequence>
                                </xs:complexType>
                            </xs:element>
                        </xs:schema>
                    </wsdl:types>
                    <wsdl:portType name="EmployeeSoapPortType">
                        <wsdl:operation name="getEmployeeDetails">
                            <wsdl:input message="tns:getEmployeeDetailsRequest"/>
                            <wsdl:output message="tns:SoapEmployeeResponse"/>
                        </wsdl:operation>
                        <wsdl:operation name="evaluateEmployee">
                            <wsdl:input message="tns:evaluateEmployeeRequest"/>
                            <wsdl:output message="tns:SoapEmployeeResponse"/>
                        </wsdl:operation>
                    </wsdl:portType>
                    <wsdl:binding name="EmployeeSoapBinding" type="tns:EmployeeSoapPortType">
                        <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
                        <wsdl:operation name="getEmployeeDetails">
                            <soap:operation soapAction="getEmployeeDetailsRequest"/>
                        </wsdl:operation>
                        <wsdl:operation name="evaluateEmployee">
                            <soap:operation soapAction="evaluateEmployeeRequest"/>
                        </wsdl:operation>
                    </wsdl:binding>
                    <wsdl:service name="EmployeeSoapWebService">
                        <wsdl:port name="EmployeeSoapPort" binding="tns:EmployeeSoapBinding">
                            <soap:address location="http://localhost:8080/ws/employees"/>
                        </wsdl:port>
                    </wsdl:service>
                </wsdl:definitions>
                """;
        return ResponseEntity.ok().contentType(MediaType.TEXT_XML).body(wsdlXml);
    }

    /**
     * Handles inbound SOAP XML POST Requests from Appian Integration Objects.
     */
    @PostMapping(consumes = {MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE, "*/*"}, produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<String> handleSoapEnvelope(@RequestBody String xmlRequestBody) {
        LOG.info("=== [SOAP HTTP Controller] Received HTTP SOAP Request ===");
        LOG.info("Body snippet: {}", xmlRequestBody.length() > 200 ? xmlRequestBody.substring(0, 200) + "..." : xmlRequestBody);

        SoapEmployeeRequest req = new SoapEmployeeRequest();
        SoapEmployeeResponse resp;

        if (xmlRequestBody.contains("evaluateEmployeeRequest") || xmlRequestBody.contains("evaluateEmployee")) {
            req.setFirstName(extractXmlTag(xmlRequestBody, "firstName", "John"));
            req.setLastName(extractXmlTag(xmlRequestBody, "lastName", "Doe"));
            req.setEmail(extractXmlTag(xmlRequestBody, "email", "john.doe@enterprise.com"));
            req.setDepartment(extractXmlTag(xmlRequestBody, "department", "IT"));
            String salaryStr = extractXmlTag(xmlRequestBody, "salary", "95000");
            try {
                req.setSalary(new BigDecimal(salaryStr));
            } catch (Exception e) {
                req.setSalary(BigDecimal.valueOf(95000));
            }
            resp = soapWebService.evaluateEmployee(req);
        } else {
            String empId = extractXmlTag(xmlRequestBody, "employeeId", "101");
            req.setEmployeeId(empId);
            resp = soapWebService.getEmployeeDetails(req);
        }

        String soapResponseBody = String.format("""
                <?xml version="1.0" encoding="UTF-8"?>
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://soap.employee.enterprise.appian.com/">
                   <soap:Body>
                      <tns:SoapEmployeeResponse>
                         <tns:employeeId>%s</tns:employeeId>
                         <tns:fullName>%s</tns:fullName>
                         <tns:email>%s</tns:email>
                         <tns:title>%s</tns:title>
                         <tns:salary>%s</tns:salary>
                         <tns:status>%s</tns:status>
                         <tns:responseCode>%s</tns:responseCode>
                      </tns:SoapEmployeeResponse>
                   </soap:Body>
                </soap:Envelope>
                """,
                resp.getEmployeeId() != null ? resp.getEmployeeId() : "101",
                resp.getFullName() != null ? resp.getFullName() : "John Doe",
                resp.getEmail() != null ? resp.getEmail() : "john.doe@enterprise.com",
                resp.getTitle() != null ? resp.getTitle() : "Software Engineer",
                resp.getSalary() != null ? resp.getSalary().toString() : "95000",
                resp.getStatus() != null ? resp.getStatus() : "ACTIVE",
                resp.getResponseCode() != null ? resp.getResponseCode() : "SUCCESS_200"
        );

        return ResponseEntity.ok().contentType(MediaType.TEXT_XML).body(soapResponseBody);
    }

    private String extractXmlTag(String xml, String tagName, String defaultValue) {
        String openTag = "<" + tagName + ">";
        String closeTag = "</" + tagName + ">";
        int start = xml.indexOf(openTag);
        if (start == -1) {
            openTag = ":" + tagName + ">";
            start = xml.indexOf(openTag);
        }
        if (start != -1) {
            int valueStart = start + openTag.length();
            int end = xml.indexOf(closeTag, valueStart);
            if (end == -1) {
                end = xml.indexOf("</", valueStart);
            }
            if (end != -1) {
                return xml.substring(valueStart, end).trim();
            }
        }
        return defaultValue;
    }
}
