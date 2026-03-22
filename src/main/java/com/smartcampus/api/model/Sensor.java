/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.model;

/**
 *
 * @author Akhash Vivekanantha
 */

/**
 * Sensor model class
 * Represents a sensor deployed in a room
 */

public class Sensor {

    // Unique sensor identifier, e.g. "TEMP-001"
    private String id;

    // Sensor category, e.g. "Temperature", "Occupancy", "CO2"
    private String type;

    // Current sensor state: ACTIVE, MAINTENANCE, or OFFLINE
    private String status;

    // Latest recorded value from this sensor
    private double currentValue;

    // ID of the room this sensor belongs to
    private String roomId;

    /**
     * Default constructor
     * Required for JSON serialization/deserialization
     */
    public Sensor() {
    }

    /**
     * Parameterized constructor
     */
    public Sensor(String id, String type, String status, double currentValue, String roomId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.currentValue = currentValue;
        this.roomId = roomId;
    }

    /**
     * Get sensor ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set sensor ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get sensor type
     */
    public String getType() {
        return type;
    }

    /**
     * Set sensor type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get sensor status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set sensor status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get current sensor value
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /**
     * Set current sensor value
     */
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Get room ID
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * Set room ID
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }    
}
