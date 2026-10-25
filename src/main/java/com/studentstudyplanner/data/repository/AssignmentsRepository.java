
package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.AssignmentEntity;

/**
 * Provides database operations for assignment records.
 *
 * This repository extends Spring Data's ListCrudRepository
 * to support creating, retrieving, updating, and deleting
 * AssignmentEntity objects in the database.
 *
 * The repository uses Long values to identify individual
 * assignment records by their unique IDs.
 */
public interface AssignmentsRepository extends ListCrudRepository<AssignmentEntity, Long> {
}
