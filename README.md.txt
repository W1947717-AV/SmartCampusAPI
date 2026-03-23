# SmartCampusAPI

## **Overview**
This project implements a RESTful API for managing a smart campus environment. It provides functionality for managing rooms, sensors, and sensor readings.  
The API is built using Java, JAX-RS (Jersey), and deployed on Apache Tomcat. It follows REST principles and supports CRUD operations, nested resources, and proper error handling.  

## **How to Run**
Open the project in Apache NetBeans IDE 18.  
Ensure Apache Tomcat server is configured.  
Right-click the project and select "Run".  
The API will be available at:  
http://localhost:8080/SmartCampusAPI/api/v1/

### **Example API Endpoints**
GET /api/v1 → API discovery
GET /api/v1/rooms → list rooms
GET /api/v1/sensors → list sensors
GET /api/v1/sensors?type=Temperature → filter sensors
GET /api/v1/sensors/{id}/readings → sensor readings




