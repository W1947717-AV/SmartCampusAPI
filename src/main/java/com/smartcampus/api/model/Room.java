/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author akhas
 */

/**
 * Room model class
 * Represents a physical room in the Smart Campus system
 */

public class Room {
    
    // Unique room identifier, e.g. "LIB-301"
    private String id;

    // Human-readable room name, e.g. "Library Quiet Study"
    private String name;

    // Maximum room capacity
    private int capacity;

    // List of sensor IDs assigned to this room
    private List<String> sensorIds = new ArrayList<>();

    /**
     * Default constructor
     * Required for JSON serialization/deserialization
     */
    public Room() {
    }

    /**
     * Parameterized constructor
     */
    public Room(String id, String name, int capacity, List<String> sensorIds) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.sensorIds = sensorIds;
    }

    /**
     * Get room ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set room ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get room name
     */
    public String getName() {
        return name;
    }

    /**
     * Set room name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get room capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set room capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get sensor IDs
     */
    public List<String> getSensorIds() {
        return sensorIds;
    }

    /**
     * Set sensor IDs
     */
    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = sensorIds;
    }    
}
