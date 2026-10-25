
package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.CourseEntity;

/**
 * Provides database operations for course records.
 *
 * This repository extends Spring Data's ListCrudRepository
 * to support creating, retrieving, updating, and deleting
 * CourseEntity objects stored in the database.
 *
 * The repository uses Long values to identify individual
 * course records by their unique IDs.
 */
public interface CoursesRepository extends ListCrudRepository<CourseEntity, Long> {
}
