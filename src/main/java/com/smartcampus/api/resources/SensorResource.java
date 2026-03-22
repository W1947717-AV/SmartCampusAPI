/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.database.MockDatabase;
import com.smartcampus.api.exception.DataNotFoundException;
import com.smartcampus.api.exception.LinkedResourceNotFoundException;
import com.smartcampus.api.model.Sensor;
import com.smartcampus.api.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author akhas
 */
/**
 * SensorResource class
 * Handles REST API endpoints for Sensor operations
 */
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    /**
     * GET all sensors
     * Optional filtering by sensor type using query parameter
     */
    @GET
    public List<Sensor> getAllSensors(@QueryParam("type") String type) {

        // If no filter is provided, return all sensors
        if (type == null || type.trim().isEmpty()) {
            return MockDatabase.sensors;
        }

        // Otherwise, filter sensors by type
        return MockDatabase.sensors.stream()
                .filter(sensor -> sensor.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * GET sensor by ID
     */
    @GET
    @Path("/{id}")
    public Sensor getSensorById(@PathParam("id") String id) {
        return MockDatabase.sensors.stream()
                .filter(sensor -> sensor.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new DataNotFoundException("Sensor with ID " + id + " not found"));
    }

    /**
     * GET sensors by room ID (useful for coursework)
     */
    @GET
    @Path("/room/{roomId}")
    public List<Sensor> getSensorsByRoom(@PathParam("roomId") String roomId) {
        return MockDatabase.sensors.stream()
                .filter(sensor -> sensor.getRoomId().equals(roomId))
                .collect(Collectors.toList());
    }

    /**
     * POST - Add new sensor
     */
    @POST
    public Sensor addSensor(Sensor sensor) {

        // Check that the referenced room exists
        boolean roomExists = false;
        for (Room room : MockDatabase.rooms) {
            if (room.getId().equals(sensor.getRoomId())) {
                roomExists = true;
                break;
            }
        }

        if (!roomExists) {
            throw new LinkedResourceNotFoundException(
                    "Cannot create sensor because room with ID " + sensor.getRoomId() + " does not exist"
            );
        }

        MockDatabase.sensors.add(sensor);
        return sensor;
    }

    /**
     * PUT - Update sensor
     */
    @PUT
    @Path("/{id}")
    public Sensor updateSensor(@PathParam("id") String id, Sensor updatedSensor) {
        for (int i = 0; i < MockDatabase.sensors.size(); i++) {
            if (MockDatabase.sensors.get(i).getId().equals(id)) {
                updatedSensor.setId(id);
                MockDatabase.sensors.set(i, updatedSensor);
                return updatedSensor;
            }
        }
        throw new DataNotFoundException("Sensor with ID " + id + " not found");
    }

    /**
     * DELETE - Remove sensor
     */
    @DELETE
    @Path("/{id}")
    public String deleteSensor(@PathParam("id") String id) {
        boolean removed = MockDatabase.sensors.removeIf(sensor -> sensor.getId().equals(id));

        if (!removed) {
            throw new DataNotFoundException("Sensor with ID " + id + " not found");
        }

        return "Sensor with ID " + id + " deleted successfully";
    }
    
    /**
     * Sub-resource locator for sensor readings
     * Delegates /sensors/{sensorId}/readings requests to SensorReadingResource
     */
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}