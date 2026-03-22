/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.exception;

/**
 *
 * @author Akhash Vivekanantha
 */

/**
 * Custom exception thrown when a sensor cannot accept readings
 */
public class SensorUnavailableException extends RuntimeException {

    /**
     * Constructor
     * @param message error message
     */
    public SensorUnavailableException(String message) {
        super(message);
    }
}