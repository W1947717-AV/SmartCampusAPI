/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.model;

/**
 *
 * @author akhash
 */

/**
 * Student model class
 * Represents a student entity in the SmartCampus system
 */

public class Student implements BaseModel {
    
    // Unique identifier for the student
    private int id;

    // Student name
    private String name;

    // Student email address
    private String email;

    /**
     * Default constructor , Required for JSON
     */
    public Student() {
    }

    /**
     * Parameterized constructor
     * Used to create a student with all fields
     */
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Get student ID
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Set student ID
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get student name
     */
    public String getName() {
        return name;
    }

    /**
     * Set student name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get student email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set student email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
}
