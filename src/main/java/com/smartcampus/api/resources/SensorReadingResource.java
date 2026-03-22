/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.database.MockDatabase;
import com.smartcampus.api.exception.DataNotFoundException;
import com.smartcampus.api.exception.SensorUnavailableException;
import com.smartcampus.api.model.Sensor;
import com.smartcampus.api.model.SensorReading;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Akhash Vivekanantha
 */

/**
 * SensorReadingResource class
 * Handles historical readings for a specific sensor
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    // Parent sensor ID from the URL context
    private String sensorId;

    /**
     * Constructor
     * Receives the parent sensor ID from SensorResource
     */
    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * GET all readings for this sensor
     */
    @GET
    public List<SensorReading> getAllReadings() {

        // Check that the parent sensor exists
        Sensor sensor = findSensorById(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor with ID " + sensorId + " not found");
        }

        // Return readings, or an empty list if none exist yet
        return MockDatabase.sensorReadings.getOrDefault(sensorId, new ArrayList<>());
    }

    /**
     * POST a new reading for this sensor
     * Also updates the parent sensor's currentValue
     */
    @POST
    public SensorReading addReading(SensorReading reading) {

        // Check that the parent sensor exists
        Sensor sensor = findSensorById(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor with ID " + sensorId + " not found");
        }
        
        // Business rule:
        // sensors in MAINTENANCE cannot accept new readings
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException(
                    "Sensor with ID " + sensorId + " is currently in MAINTENANCE and cannot accept new readings"
            );
        }        

        // Auto-generate a reading ID if missing
        if (reading.getId() == null || reading.getId().trim().isEmpty()) {
            reading.setId(UUID.randomUUID().toString());
        }

        // Store the reading under this sensor
        MockDatabase.sensorReadings
                .computeIfAbsent(sensorId, key -> new ArrayList<>())
                .add(reading);

        // Side effect required by coursework:
        // update parent sensor currentValue
        sensor.setCurrentValue(reading.getValue());

        return reading;
    }

    /**
     * Helper method to find a sensor by ID
     */
    private Sensor findSensorById(String id) {
        for (Sensor sensor : MockDatabase.sensors) {
            if (sensor.getId().equals(id)) {
                return sensor;
            }
        }
        return null;
    }
}
