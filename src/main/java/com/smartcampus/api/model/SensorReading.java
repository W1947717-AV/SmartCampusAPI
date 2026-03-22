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
 * SensorReading model class
 * Represents a historical reading captured by a sensor
 */

public class SensorReading {

    // Unique reading ID (UUID recommended by coursework)
    private String id;

    // Epoch timestamp in milliseconds
    private long timestamp;

    // Actual recorded value
    private double value;

    /**
     * Default constructor
     * Required for JSON serialization/deserialization
     */
    public SensorReading() {
    }

    /**
     * Parameterized constructor
     */
    public SensorReading(String id, long timestamp, double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

    /**
     * Get reading ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set reading ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Set timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get reading value
     */
    public double getValue() {
        return value;
    }

    /**
     * Set reading value
     */
    public void setValue(double value) {
        this.value = value;
    }    
}
