/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.database.MockDatabase;
import com.smartcampus.api.exception.DataNotFoundException;
import com.smartcampus.api.exception.RoomNotEmptyException;
import com.smartcampus.api.model.Room;
import com.smartcampus.api.model.Sensor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/**
 *
 * @author akhas
 */

/**
 * SensorRoomResource class
 Handles REST API endpoints for Room operations
 */
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorRoomResource {

    /**
     * GET all rooms
     */
    @GET
    public List<Room> getAllRooms() {
        return MockDatabase.rooms;
    }

    /**
     * GET room by ID
     */
    @GET
    @Path("/{id}")
    public Room getRoomById(@PathParam("id") String id) {
        for (Room room : MockDatabase.rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        throw new DataNotFoundException("Room with ID " + id + " not found");
    }

    /**
     * POST - Add new room
     */
    @POST
    public Room addRoom(Room room) {
        MockDatabase.rooms.add(room);
        return room;
    }

    /**
     * PUT - Update room
     */
    @PUT
    @Path("/{id}")
    public Room updateRoom(@PathParam("id") String id, Room updatedRoom) {
        for (int i = 0; i < MockDatabase.rooms.size(); i++) {
            if (MockDatabase.rooms.get(i).getId().equals(id)) {
                updatedRoom.setId(id);
                MockDatabase.rooms.set(i, updatedRoom);
                return updatedRoom;
            }
        }
        throw new DataNotFoundException("Room with ID " + id + " not found");
    }

    /**
     * DELETE - Remove room
     */
    @DELETE
    @Path("/{id}")
    public String deleteRoom(@PathParam("id") String id) {

        // Check if the room exists first
        Room roomToDelete = null;
        for (Room room : MockDatabase.rooms) {
            if (room.getId().equals(id)) {
                roomToDelete = room;
                break;
            }
        }

        if (roomToDelete == null) {
            throw new DataNotFoundException("Room with ID " + id + " not found");
        }

        // Business rule:
        // room cannot be deleted if it still has sensors assigned
        for (Sensor sensor : MockDatabase.sensors) {
            if (sensor.getRoomId().equals(id) && "ACTIVE".equalsIgnoreCase(sensor.getStatus())) {
                throw new RoomNotEmptyException(
                        "Room with ID " + id + " cannot be deleted because it still has active sensors assigned"
                );
            }
        }

        MockDatabase.rooms.remove(roomToDelete);
        return "Room with ID " + id + " deleted successfully";
    }
}
