
package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.NoteEntity;

/**
 * Provides database operations for note records.
 *
 * This repository extends Spring Data's ListCrudRepository
 * to support creating, retrieving, updating, and deleting
 * NoteEntity objects stored in the database.
 *
 * The repository uses Long values to identify individual
 * note records by their unique IDs.
 */
public interface NotesRepository extends ListCrudRepository<NoteEntity, Long> {
}
