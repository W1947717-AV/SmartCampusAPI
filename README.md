# SmartCampusAPI

## **Overview**
This project implements a RESTful API for managing a smart campus environment. It provides functionality for managing rooms, sensors, and sensor readings.  
The API is built using Java, JAX-RS (Jersey), and deployed on Apache Tomcat. It follows REST principles and supports CRUD operations, nested resources, and proper error handling.

Main resources:  
- Rooms  
- Sensors  
- Sensor readings  

Main features:  
- API discovery endpoint at `/api/v1`  
- Room management  
- Sensor management  
- Sensor filtering using query parameters  
- Nested sensor readings resource  
- Error handling with JSON responses  
- Logging using JAX-RS filters  

## **How to Run**
1. Open the project in Apache NetBeans IDE 18.  
2. Make sure Apache Tomcat is configured in NetBeans.  
3. Clean and build the project.  
4. Right-click the project and select **Run**.  
5. Open the API in a browser or Postman using:  

 `http://localhost:8080/SmartCampusAPI/api/v1`

### **Example API Endpoints**
GET /api/v1 → API discovery  
GET /api/v1/rooms → list rooms  
GET /api/v1/sensors → list sensors  
GET /api/v1/sensors?type=Temperature → filter sensors  
GET /api/v1/sensors/{id}/readings → sensor readings

## Sample curl Commands  
### 1. Discovery endpoint
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1

### 2. Get all rooms
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/rooms

### 3. Get a specific room
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/rooms/LIB-301

### 4. Get all sensors
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/sensors

### 5. Filter sensors by type
curl -X GET "http://localhost:8080/SmartCampusAPI/api/v1/sensors?type=Temperature"

### 6. Get readings for a sensor
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/sensors/TEMP-001/readings

### 7. Add a new reading
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors/TEMP-001/readings -H "Content-Type: application/json" -d "{\"value\":24.2,\"timestamp\":1711000000000}"

### 8. Try deleting a room with active sensors
curl -X DELETE http://localhost:8080/SmartCampusAPI/api/v1/rooms/LIB-301

