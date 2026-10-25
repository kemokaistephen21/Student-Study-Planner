
package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.TaskEntity;

/**
 * Provides database operations for study task records.
 *
 * This repository extends Spring Data's ListCrudRepository
 * to support creating, retrieving, updating, and deleting
 * TaskEntity objects stored in the database.
 *
 * The repository uses Long values to identify individual
 * study task records by their unique IDs.
 */
public interface TasksRepository extends ListCrudRepository<TaskEntity, Long> {
}
