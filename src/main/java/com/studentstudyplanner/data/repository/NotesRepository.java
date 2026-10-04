package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.NoteEntity;

public interface NotesRepository extends ListCrudRepository<NoteEntity, Long> {
}