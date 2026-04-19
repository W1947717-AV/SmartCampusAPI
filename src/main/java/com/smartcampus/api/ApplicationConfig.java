package com.smartcampus.api;

import com.smartcampus.api.exception.DataNotFoundExceptionMapper;
import com.smartcampus.api.exception.RoomNotEmptyExceptionMapper;
import com.smartcampus.api.exception.LinkedResourceNotFoundExceptionMapper;
import com.smartcampus.api.exception.SensorUnavailableExceptionMapper;
import com.smartcampus.api.exception.GlobalExceptionMapper;
import com.smartcampus.api.filter.LoggingFilter;
import com.smartcampus.api.resources.SensorRoomResource;
import com.smartcampus.api.resources.SensorResource;
import com.smartcampus.api.resources.StudentResource;
import com.smartcampus.api.resources.DiscoveryResource;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.Registers REST resources and provider clases
 * @author Akhash Vivekanantha
 */
@ApplicationPath("/api/v1")
public class ApplicationConfig extends Application {

    /**
     * Register resource and provider classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        // Resources
        classes.add(StudentResource.class);
        classes.add(SensorRoomResource.class);
        classes.add(SensorResource.class);
        classes.add(DiscoveryResource.class);

        // Providers
        classes.add(DataNotFoundExceptionMapper.class);
        classes.add(RoomNotEmptyExceptionMapper.class);
        classes.add(LinkedResourceNotFoundExceptionMapper.class);
        classes.add(SensorUnavailableExceptionMapper.class);
        classes.add(GlobalExceptionMapper.class);
        classes.add(LoggingFilter.class);

        return classes;
    }
}