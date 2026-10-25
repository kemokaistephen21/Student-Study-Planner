
package com.studentstudyplanner.data;

import java.util.List;

/**
 * Defines common database operations for the
 * Student Study Planner application.
 *
 * This interface provides methods for creating,
 * retrieving, updating, and deleting records.
 *
 * @param <T> the type of entity being managed
 */
public interface DataAccessInterface<T> {

    /**
     * Retrieves all records of the specified entity type.
     *
     * @return a list of all records
     */
    public List<T> findAll();

    /**
     * Finds a record using its unique database ID.
     *
     * @param id the ID of the record to find
     * @return the matching record, or null if not found,
     *         depending on the implementation
     */
    public T findById(Long id);

    /**
     * Creates a new record in the database.
     *
     * @param t the entity to create
     */
    public void create(T t);

    /**
     * Updates an existing record in the database.
     *
     * @param t the entity containing updated information
     */
    public void update(T t);

    /**
     * Deletes an existing record from the database.
     *
     * @param t the entity to delete
     */
    public void delete(T t);
}

