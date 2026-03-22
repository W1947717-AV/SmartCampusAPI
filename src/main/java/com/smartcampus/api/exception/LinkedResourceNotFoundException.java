/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.exception;

/**
 *
 * @author akhas
 */

/**
 * Custom exception thrown when a linked resource does not exist
 */
public class LinkedResourceNotFoundException extends RuntimeException {

    /**
     * Constructor
     * @param message error message
     */
    public LinkedResourceNotFoundException(String message) {
        super(message);
    }
}