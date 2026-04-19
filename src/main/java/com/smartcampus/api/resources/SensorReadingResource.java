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
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * SensorReadingResource class
 * Handles historical readings for a specific sensor
 * @author Akhash Vivekanantha
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * GET all readings for this sensor
     */
    @GET
    public List<SensorReading> getAllReadings() {
        Sensor sensor = findSensorById(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor with ID " + sensorId + " not found");
        }
        return MockDatabase.sensorReadings.getOrDefault(sensorId, new ArrayList<>());
    }

    /**
     * POST a new reading for this sensor
     * Blocks if sensor is in MAINTENANCE or OFFLINE status
     * Updates parent sensor's currentValue as a side effect
     * Returns 201 Created on success
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {

        Sensor sensor = findSensorById(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor with ID " + sensorId + " not found");
        }

        // Business rule: MAINTENANCE and OFFLINE sensors cannot accept new readings
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus()) ||
            "OFFLINE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException(
                "Sensor with ID " + sensorId + " is currently " + sensor.getStatus() + " and cannot accept new readings"
            );
        }

        // Auto-generate reading ID if missing
        if (reading.getId() == null || reading.getId().trim().isEmpty()) {
            reading.setId(UUID.randomUUID().toString());
        }

        // Auto-set timestamp if missing
        if (reading.getTimestamp() == 0) {
            reading.setTimestamp(System.currentTimeMillis());
        }

        MockDatabase.sensorReadings
                .computeIfAbsent(sensorId, key -> new ArrayList<>())
                .add(reading);

        // Side effect: update parent sensor's currentValue
        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED).entity(reading).build();
    }

    private Sensor findSensorById(String id) {
        for (Sensor sensor : MockDatabase.sensors) {
            if (sensor.getId().equals(id)) {
                return sensor;
            }
        }
        return null;
    }
}