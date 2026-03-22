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
 * Custom exception class
 * Thrown when requested data cannot be found
 */
public class DataNotFoundException extends RuntimeException {

    /**
     * Constructor
     * @param message error message
     */
    public DataNotFoundException(String message) {
        super(message);
    }
}
