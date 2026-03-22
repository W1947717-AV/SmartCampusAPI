/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.api.dao;

import com.smartcampus.api.model.BaseModel;
import java.util.List;
/**
 *
 * @author akhash
 */

/**
 * Generic DAO class
 * Provides reusable CRUD operations for any model that implements BaseModel
 */
public class GenericDAO<T extends BaseModel> {

    // List to store data (passed from MockDatabase)
    private List<T> list;

    /**
     * Constructor
     * Takes a list as the data source
     */
    public GenericDAO(List<T> list) {
        this.list = list;
    }

    /**
     * Get all records
     */
    public List<T> getAll() {
        return list;
    }

    /**
     * Get a single record by ID
     */
    public T getById(int id) {
        for (T item : list) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // if not found
    }

    /**
     * Add a new record
     */
    public T add(T item) {
        list.add(item);
        return item;
    }

    /**
     * Update an existing record
     */
    public T update(int id, T newItem) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                newItem.setId(id);
                list.set(i, newItem);
                return newItem;
            }
        }
        return null; // if not found
    }

    /**
     * Delete a record by ID
     */
    public boolean delete(int id) {
        return list.removeIf(item -> item.getId() == id);
    }
}