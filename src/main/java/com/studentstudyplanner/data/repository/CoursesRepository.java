package com.studentstudyplanner.data.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.studentstudyplanner.data.entity.CourseEntity;

public interface CoursesRepository extends ListCrudRepository<CourseEntity, Long> {
}