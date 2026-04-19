/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.database;

import com.smartcampus.api.model.Student;
import com.smartcampus.api.model.Room;
import com.smartcampus.api.model.Sensor;
import com.smartcampus.api.model.SensorReading;

import java.util.*;
/**
 * @author Akhash Vivekanantha
 */

/**
 * Mock database class, Stores application data in memory using Java lists
 */
public class MockDatabase {

    // ======= In-memory list of students ===========
    public static List<Student> students = new ArrayList<>();

    // ================= ROOMS =================
    public static List<Room> rooms = new ArrayList<>();

    // ================= SENSORS =================
    public static List<Sensor> sensors = new ArrayList<>();

    // ================= SENSOR READINGS =================
    // Map: sensorId -> list of readings
    public static Map<String, List<SensorReading>> sensorReadings = new HashMap<>();

    /**
     * Static block to initialise sample data
     */
    static {

    // ===== Students (keep for testing) =====
    students.add(new Student(1, "Ali Safir", "ali.safir@westminster.com"));
    students.add(new Student(2, "Sara Mike", "sara.mike@westminster.com"));
    students.add(new Student(3, "John Smith", "john.smith@westminster.com"));

    // ===== Rooms =====
    rooms.add(new Room("LIB-301", "Library Room", 100, new ArrayList<>()));
    rooms.add(new Room("LAB-201", "Computer Lab", 50, new ArrayList<>()));
    rooms.add(new Room("ENG-101", "Engineering Lecture Hall", 120, new ArrayList<>()));
    rooms.add(new Room("SCI-202", "Science Lab", 60, new ArrayList<>()));
    
    rooms.add(new Room("TEST-100", "Test Room", 20, new ArrayList<>())); //For Test

    // ===== Active Sensors =====
    sensors.add(new Sensor("TEMP-001", "Temperature", "ACTIVE", 22.5, "LIB-301"));
    sensors.add(new Sensor("TEMP-002", "Temperature", "ACTIVE", 21.0, "ENG-101"));
    sensors.add(new Sensor("OCC-001", "Occupancy", "ACTIVE", 35, "LAB-201"));
    sensors.add(new Sensor("CO2-001", "CO2", "ACTIVE", 400, "SCI-202"));

    // ===== Maintenance sensors =====
    sensors.add(new Sensor("TEMP-003", "Temperature", "MAINTENANCE", 0, "SCI-202"));

    // ===== Offline sensors ======
    sensors.add(new Sensor("OCC-002", "Occupancy", "OFFLINE", 0, "LIB-301"));
    sensors.add(new Sensor("CO2-002", "CO2", "OFFLINE", 0, "ENG-101"));
    
    sensors.add(new Sensor("TEST-SEN-001", "Temperature", "OFFLINE", 0, "TEST-100")); // For Test
    
    // ===== Sensor Readings =====
    sensorReadings.put("TEMP-001", new ArrayList<>());
    sensorReadings.get("TEMP-001").add(
            new SensorReading(UUID.randomUUID().toString(), System.currentTimeMillis(), 22.5)
    );
    sensorReadings.get("TEMP-001").add(
            new SensorReading(UUID.randomUUID().toString(), System.currentTimeMillis(), 23.1)
    );

    sensorReadings.put("TEMP-002", new ArrayList<>());
    sensorReadings.get("TEMP-002").add(
            new SensorReading(UUID.randomUUID().toString(), System.currentTimeMillis(), 21.0)
    );

    sensorReadings.put("OCC-001", new ArrayList<>());
    sensorReadings.get("OCC-001").add(
            new SensorReading(UUID.randomUUID().toString(), System.currentTimeMillis(), 35)
    );
    sensorReadings.get("OCC-001").add(
            new SensorReading(UUID.randomUUID().toString(), System.currentTimeMillis(), 40)
    );

    sensorReadings.put("CO2-001", new ArrayList<>());
    sensorReadings.get("CO2-001").add(
            new SensorReading(UUID.randomUUID().toString(), System.currentTimeMillis(), 400)
    );

    sensorReadings.put("TEMP-003", new ArrayList<>());
}
}
