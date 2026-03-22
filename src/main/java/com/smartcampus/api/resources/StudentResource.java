/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.resources;

import com.smartcampus.api.dao.GenericDAO;
import com.smartcampus.api.database.MockDatabase;
import com.smartcampus.api.model.Student;
import com.smartcampus.api.exception.DataNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
/**
 *
 * @author akhas
 */

/**
 * StudentResource class
 * Handles REST API endpoints for Student operations
 */
@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    // DAO instance using MockDatabase
    private GenericDAO<Student> studentDAO = new GenericDAO<>(MockDatabase.students);

    /**
     * GET all students
     */
    @GET
    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    /**
    * GET student by ID
    */
    @GET
    @Path("/{id}")
    public Student getStudentById(@PathParam("id") int id) {
        Student student = studentDAO.getById(id);

        if (student == null) {
            throw new DataNotFoundException("Student with ID " + id + " not found");
        }

        return student;
}

    /**
     * POST - Add new student
     */
    @POST
    public Student addStudent(Student student) {
        return studentDAO.add(student);
    }

    /**
     * PUT - Update student
     */
    @PUT
    @Path("/{id}")
    public Student updateStudent(@PathParam("id") int id, Student student) {
        Student updatedStudent = studentDAO.update(id, student);

        if (updatedStudent == null) {
            throw new DataNotFoundException("Student with ID " + id + " not found");
        }

        return updatedStudent;
    }

    /**
     * DELETE - Remove student
     */
    @DELETE
    @Path("/{id}")
    public String deleteStudent(@PathParam("id") int id) {
        boolean deleted = studentDAO.delete(id);

        if (!deleted) {
            throw new DataNotFoundException("Student with ID " + id + " not found");
        }

        return "Student with ID " + id + " deleted successfully";
    }
}