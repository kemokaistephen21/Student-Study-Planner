package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.AssignmentEntity;

public interface AssignmentsRepository extends ListCrudRepository<AssignmentEntity, Long> {
}