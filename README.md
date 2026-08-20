# Enterprise Java Repository: Appian Integration Patterns & Struts 2 (Employee Management)

This repository provides a production-grade enterprise Java reference implementation showcasing **all Appian integration architecture patterns**, **Apache Struts 2 framework integration**, and a complete **Appian Export deployment package** (`appian-export/`) for an **Employee Management Application**.

---

## 🏗️ Architecture & Appian Pattern Mapping

| Pattern / Technology | Java / Appian Implementation File | Description / Functionality |
| :--- | :--- | :--- |
| **Appian Custom Jar & Plug-in** | [`EmployeeAuditLoggerSmartService.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/plugin/smartservice/EmployeeAuditLoggerSmartService.java)<br>[`EmployeeBonusCalculatorSmartService.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/plugin/smartservice/EmployeeBonusCalculatorSmartService.java)<br>[`EmployeeExpressionFunctions.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/plugin/function/EmployeeExpressionFunctions.java)<br>[`appian-plugin.xml`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/resources/META-INF/appian-plugin.xml) | Implements custom Appian Process Model Smart Services (`@SmartService`, `@Input`, `@Output`) and SAIL Expression Functions (`fn!calculateEmployeeBonus`, `fn!validateEmployeeSSN`) registered via `appian-plugin.xml`. |
| **Apache Struts 2 Framework** | [`EmployeeStrutsAction.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/struts/EmployeeStrutsAction.java)<br>[`struts.xml`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/resources/struts.xml) | Implements classic enterprise Struts 2 Action controllers (`execute`, `saveEmployee`, `deleteEmployee`) and result mappings. |
| **REST Web API & Integration** | [`EmployeeWebApiController.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/rest/controller/EmployeeWebApiController.java)<br>[`EmployeeExternalRestClient.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/rest/client/EmployeeExternalRestClient.java) | Represents Appian REST Web API endpoints (`/api/v1/employees`) and outbound HTTP Connected Systems. |
| **SOAP Web Service** | [`EmployeeSoapWebService.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/soap/endpoint/EmployeeSoapWebService.java)<br>[`SoapEmployeeRequest.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/soap/model/SoapEmployeeRequest.java) | Standard JAX-WS WSDL SOAP Web Service endpoint (`http://localhost:8088/ws/employees?wsdl`) for enterprise SOAP integration. |
| **JMS Message Queue** | [`EmployeeJmsMessageListener.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/jms/listener/EmployeeJmsMessageListener.java)<br>[`EmployeeJmsPublisher.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/jms/publisher/EmployeeJmsPublisher.java) | ActiveMQ JMS queue integration (`employee.events.queue`) triggering asynchronous process events. |
| **Java Web Servlet** | [`EmployeeExportServlet.java`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/java-service/src/main/java/com/appian/enterprise/employee/servlet/EmployeeExportServlet.java) | Direct HTTP Java Servlet (`/servlet/export-employees`) streaming dynamic CSV employee reports. |
| **Appian SAIL Forms** | [`Employee_Create_Edit_Form.sail`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/appian-export/forms/Employee_Create_Edit_Form.sail)<br>[`Employee_Dashboard_Summary.sail`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/appian-export/forms/Employee_Dashboard_Summary.sail)<br>[`Employee_Approval_Task_Form.sail`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/appian-export/forms/Employee_Approval_Task_Form.sail) | Native Appian SAIL interface definitions for input forms, record dashboards, and user tasks. |
| **Appian Export Package** | [`appian-export/`](file:///c:/ramu/Project_Assignment/RapidX/FreddeMac_Project_RapidX/Work/Appian_Research_Notes/Appian_Example_All_Patterns_Ex2/appian-export) | Appian package structure containing CDTs (`Employee.xsd`), Process Models (`Employee_Onboarding_Process_Model.xml`), Web APIs, Connected Systems, and Manifest. |

---

## 📁 Repository Structure

```
Appian_Example_All_Patterns_Ex2/
├── README.md
├── java-service/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/appian/enterprise/employee/
│       │   │   ├── AppianEmployeeApplication.java
│       │   │   ├── model/ (Employee.java, Department.java)
│       │   │   ├── service/ (EmployeeService.java)
│       │   │   ├── plugin/
│       │   │   │   ├── api/ (SmartService, Function, Name, Input, Output annotations)
│       │   │   │   ├── smartservice/ (EmployeeAuditLoggerSmartService, EmployeeBonusCalculatorSmartService)
│       │   │   │   └── function/ (EmployeeExpressionFunctions)
│       │   │   ├── struts/ (EmployeeStrutsAction.java)
│       │   │   ├── rest/ (EmployeeWebApiController, EmployeeExternalRestClient)
│       │   │   ├── soap/ (EmployeeSoapWebService, SoapEmployeeRequest, SoapEmployeeResponse)
│       │   │   ├── jms/ (EmployeeJmsMessageListener, EmployeeJmsPublisher)
│       │   │   └── servlet/ (EmployeeExportServlet)
│       │   └── resources/
│       │       ├── application.yml
│       │       ├── struts.xml
│       │       └── META-INF/appian-plugin.xml
│       └── test/
│           └── java/com/appian/enterprise/employee/AppianEmployeeApplicationTests.java
└── appian-export/
    ├── META-INF/MANIFEST.MF
    ├── datatype/ (Employee.xsd, Department.xsd)
    ├── processModel/ (Employee_Onboarding_Process_Model.xml)
    ├── webApi/ (getEmployeeDetails_WebApi.xml)
    ├── integration/ (ExternalHRSystem_Integration.xml)
    ├── connectedSystem/ (HR_Backend_ConnectedSystem.xml)
    └── forms/ (Employee_Create_Edit_Form.sail, Employee_Dashboard_Summary.sail, Employee_Approval_Task_Form.sail)
```

---

## 🚀 Building and Running

### 1. Build and Run Unit Tests
```bash
cd java-service
mvn clean package
```

### 2. Launch Application
```bash
cd java-service
mvn spring-boot:run
```

### 3. Test Endpoint & Pattern URIs
- **REST Web API**: `GET http://localhost:8080/api/v1/employees`
- **SOAP Web Service WSDL**: `GET http://localhost:8088/ws/employees?wsdl`
- **Web Servlet Export**: `GET http://localhost:8080/servlet/export-employees`
- **Struts Action Routing**: `/employeeStruts.action`
