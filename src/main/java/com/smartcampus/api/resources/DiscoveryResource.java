/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Akhash Vivekanantha
 */

/**
 * DiscoveryResource class
 * Provides root API metadata and navigation links
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {

    /**
     * GET root discovery endpoint
     */
    @GET
    public Map<String, Object> getApiInfo() {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("apiName", "Smart Campus API");
        response.put("version", "v1");
        response.put("status", "active");

        Map<String, String> adminContact = new HashMap<>();
        adminContact.put("name", "Akhash Vivekanantha");
        adminContact.put("email", "w1947717@westminster.ac.uk");
        response.put("adminContact", adminContact);

        Map<String, String> resources = new LinkedHashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");
        response.put("resources", resources);

        return response;
    }
}