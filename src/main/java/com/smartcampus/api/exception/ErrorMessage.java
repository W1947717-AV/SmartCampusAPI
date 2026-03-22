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
 * Error message model class
 * Used to return structured JSON error responses
 */
public class ErrorMessage {

    // Error message text
    private String errorMessage;

    // HTTP status code
    private int errorCode;

    /**
     * Default constructor
     */
    public ErrorMessage() {
    }

    /**
     * Parameterized constructor
     */
    public ErrorMessage(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Get error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Set error code
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }    
}
