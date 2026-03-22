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
 * Custom exception thrown when attempting to delete
 * a room that still has sensors assigned to it
 */
public class RoomNotEmptyException extends RuntimeException {

    /**
     * Constructor
     * @param message error message
     */
    public RoomNotEmptyException(String message) {
        super(message);
    }
}