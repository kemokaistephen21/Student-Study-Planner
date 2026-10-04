package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.TaskEntity;

public interface TasksRepository extends ListCrudRepository<TaskEntity, Long> {
}