/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.database.MockDatabase;
import com.smartcampus.api.exception.DataNotFoundException;
import com.smartcampus.api.exception.LinkedResourceNotFoundException;
import com.smartcampus.api.model.Room;
import com.smartcampus.api.model.Sensor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SensorResource class
 * Handles REST API endpoints for Sensor operations
 * @author Akhash Vivekanantha
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
        if (type == null || type.trim().isEmpty()) {
            return MockDatabase.sensors;
        }
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
                .orElseThrow(() -> new DataNotFoundException("Sensor with ID " + id + " not found"));
    }

    /**
     * GET sensors by room ID
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
     * Validates that the referenced room exists
     * Returns 201 Created on success
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor) {

        // Check that the referenced room exists
        Room foundRoom = null;
        for (Room room : MockDatabase.rooms) {
            if (room.getId().equals(sensor.getRoomId())) {
                foundRoom = room;
                break;
            }
        }

        if (foundRoom == null) {
            throw new LinkedResourceNotFoundException(
                "Cannot create sensor because room with ID " + sensor.getRoomId() + " does not exist"
            );
        }

        MockDatabase.sensors.add(sensor);

        // Keep room's sensorIds in sync
        foundRoom.getSensorIds().add(sensor.getId());

        return Response.status(Response.Status.CREATED).entity(sensor).build();
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
    public Response deleteSensor(@PathParam("id") String id) {

        // Find the sensor first so we can clean up the room's sensorIds
        Sensor toDelete = MockDatabase.sensors.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Sensor with ID " + id + " not found"));

        // Remove sensor ID from its room's sensorIds list
        for (Room room : MockDatabase.rooms) {
            if (room.getId().equals(toDelete.getRoomId())) {
                room.getSensorIds().remove(id);
                break;
            }
        }

        MockDatabase.sensors.remove(toDelete);
        return Response.ok("Sensor with ID " + id + " deleted successfully").build();
    }

    /**
     * Sub-resource locator for sensor readings
     * Delegates /sensors/{sensorId}/readings to SensorReadingResource
     */
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}