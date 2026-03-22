/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 *
 * @author Akhash Vivekanantha
 */

/**
 * Exception mapper class
 * Converts DataNotFoundException into a JSON HTTP response
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    /**
     * Maps the exception to a 404 Not Found response
     */
    @Override
    public Response toResponse(DataNotFoundException exception) {
        ErrorMessage error = new ErrorMessage(exception.getMessage(), 404);

        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
