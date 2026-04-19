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
```bash
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1
```

### 2. Get all rooms
```bash
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/rooms
```

### 3. Create a new room
```bash
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/rooms \
  -H "Content-Type: application/json" \
  -d "{\"id\":\"NEW-101\",\"name\":\"New Test Room\",\"capacity\":30}"
```

### 4. Get a specific room
```bash
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/rooms/LIB-301
```

### 5. Delete a room with sensors (expect 409)
```bash
curl -X DELETE http://localhost:8080/SmartCampusAPI/api/v1/rooms/LIB-301
```

### 6. Get all sensors
```bash
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/sensors
```

### 7. Filter sensors by type
```bash
curl -X GET "http://localhost:8080/SmartCampusAPI/api/v1/sensors?type=Temperature"
```

### 8. Create a new sensor
```bash
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors \
  -H "Content-Type: application/json" \
  -d "{\"id\":\"TEMP-999\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":20.0,\"roomId\":\"LIB-301\"}"
```

### 9. Create a sensor with invalid roomId (expect 422)
```bash
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors \
  -H "Content-Type: application/json" \
  -d "{\"id\":\"TEMP-999\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":20.0,\"roomId\":\"FAKE-999\"}"
```

### 10. Get readings for a sensor
```bash
curl -X GET http://localhost:8080/SmartCampusAPI/api/v1/sensors/TEMP-001/readings
```

### 11. Add a new reading
```bash
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors/TEMP-001/readings \
  -H "Content-Type: application/json" \
  -d "{\"value\":24.2}"
```

### 12. Post reading to MAINTENANCE sensor (expect 403)
```bash
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors/TEMP-003/readings \
  -H "Content-Type: application/json" \
  -d "{\"value\":25.0}"
```

## Conceptual Report
### Question 1.1
In JAX-RS, a new instance of a resource class is created for every incoming request, so it is not treated as a singleton by default. This follows the REST principle of statelessness, where the server does not store client specific data between requests . Because a new object is created each time, any variables defined inside the resource class are temporary and will be reset after the request is completed. This means that important application data cannot be stored inside resource classes, as it would be lost on the next request.

To keep data consistent across requests, shared data structures such as maps or lists must be stored in a separate shared component, such as a DAO or a static mock database. However, since multiple requests can access and modify this shared data at the same time, there is a risk of race conditions and data inconsistency. To prevent this, developers must make sure that access to shared data is properly managed, for example by using thread safe collections or synchronising access when updates are made. Overall, the per-request lifecycle improves scalability and isolation between requests, but requires careful handling of shared data to maintain consistency.

### Question 1.2
Hypermedia, also known as HATEOAS, refers to including links and navigation options within API responses so that clients can discover available actions dynamically. Instead of simply returning data, the API also provides information about what the client can do next, such as accessing related resources or performing further operations. This approach is considered a key feature of advanced RESTful design because it makes the API self-descriptive and easier to use .

By including hypermedia links, client developers do not need to rely heavily on static documentation or hardcoded URLs, since they can follow the links provided in each response. This makes the system more flexible, as changes to the API structure can be handled without breaking the client, as long as the links are updated correctly. It also improves discoverability, allowing clients to navigate the API in a similar way to browsing a website. Overall, hypermedia links reduces tight coupling between the client and server and makes the API more robust and easier to maintain.

### Question 2.1
When returning a list of rooms, there are differences between returning only the IDs and returning the full room objects. If only the IDs are returned, the response is smaller, so it uses less bandwidth and is faster to send. However, the client would then need to make additional requests to get the full details of each room, which increases the amount of work on the client side. On the other hand, returning the full room objects gives all the information in one response, which is easier for the client to use and avoids extra requests. The downside is that the response is larger and may be slower if there are many rooms. Overall, returning full objects is more convenient for the client, while returning only IDs is more efficient in terms of data transfer.

### Question 2.2
Yes, the DELETE operation is idempotent in my implementation. This means that if the same DELETE request is sent multiple times, the result will always be the same after the first request. For example, if a room is successfully deleted the first time, sending the same request again will not delete anything further because the room no longer exists. Instead, the API will return an error, such as “not found.” Also, if the room cannot be deleted because it still has sensors assigned, the request will be blocked every time until that condition changes. In both cases, repeating the request does not change the system again, so the DELETE operation is considered idempotent.

### Question 3.1
The @Consumes(MediaType.APPLICATION_JSON) annotation means that the POST method only accepts request bodies that are sent as JSON. If a client tries to send data in a different format, such as text/plain or application/xml, JAX-RS will see that the request Content-Type does not match what the method is expecting. Because of this mismatch, the request will not be processed normally, and JAX-RS will usually return an HTTP 415 Unsupported Media Type response. This happens before the method logic is executed, because the framework checks whether it knows how to read the incoming format and whether it matches the media type declared on the resource method. In simple terms, the API is telling the client that the data format is wrong, even if the URL and HTTP method are correct. This is part of how JAX-RS handles request and response processing through annotations like @Consumes and @Produces.

### Question 3.2
Using @QueryParam for filtering is generally better because filtering is usually treated as an optional condition on a collection, not as a completely different resource. In this case, /api/v1/sensors is the main collection, and ?type=CO2 simply narrows down the results. This is more flexible because the same endpoint can support many different filters without needing lots of extra paths. For example, query parameters make it easy to extend the API later with things like ?type=CO2&status=ACTIVE. If the filter was placed in the path, such as /api/v1/sensors/type/CO2, the URL becomes more rigid and less natural for searching. Query parameters are therefore better for filtering and searching collections, while path parameters are usually better when identifying one specific resource, such as /api/v1/sensors/TEMP-001. This matches the JAX-RS distinction between path parameters and query parameters in endpoint design.

### Question 4.1
The Sub-Resource Locator pattern helps organise large APIs by splitting responsibilities into smaller, more focused classes instead of putting everything into one big controller. Instead of handling all nested paths inside a single resource class, the main resource delegates part of the request to another class that is responsible for that specific sub-resource. For example, a sensor resource can delegate anything related to readings to a separate SensorReadingResource class.

This approach makes the code easier to manage because each class has a clear responsibility. If everything was written in one large controller with paths like sensors/{id}/readings/{rid}, the class would become very long and difficult to read, test, and maintain. By separating the logic, developers can work on different parts of the API independently, which improves maintainability and scalability.

It also improves code organisation because related functionality is grouped together. For example, all logic related to sensor readings is kept in one place rather than being mixed with sensor logic. This follows the principle of separation of concerns, which is important in RESTful and JAX-RS design.

Overall, the Sub-Resource Locator pattern reduces complexity, makes the code cleaner, and allows the API to grow more easily without becoming difficult to manage.

### Question 5.1
HTTP 422 is considered more accurate than 404 in this case because the request itself is valid, but the data inside it has a problem. A 404 error usually means that the URL or resource being requested does not exist. However, in this situation, the endpoint exists and the request is correctly formed, but the roomId inside the JSON does not match any existing room. This means the issue is with the content of the request, not the resource path. Therefore, 422 “Unprocessable Entity” is more appropriate because it shows that the server understood the request but could not process it due to invalid data.

### Question 5.2
Exposing internal Java stack traces is a security risk because it reveals sensitive information about how the system works internally. A stack trace can show details such as class names, method names, file structures, and even line numbers in the code. This information can help an attacker understand the architecture of the system and identify weaknesses. For example, they might see which libraries or frameworks are being used and try to exploit known vulnerabilities. It can also expose database-related information or logic errors, making it easier for attackers to plan targeted attacks. For this reason, APIs should return clean error messages to users and keep detailed logs only on the server side.

### Question 5.3
Using JAX-RS filters for logging is better because it avoids repeating the same logging code in every resource method. If logging is written manually inside each method, it makes the code longer, harder to read, and more difficult to maintain. Filters allow logging to be handled in one central place, so every request and response is automatically logged without modifying individual resource classes. This follows the idea of separating concerns, where logging is handled separately from business logic. It also makes it easier to update or improve logging later, since changes only need to be made in one place instead of throughout the entire project.
