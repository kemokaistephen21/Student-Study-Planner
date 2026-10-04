package com.studentstudyplanner.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TASKS")
public class TaskEntity {
	@Id
	Long id;

	@Column(name = "NAME")
	String name;
	
	@Column(name = "DUEDATE")
	LocalDateTime dueDate;
	
	@Column(name = "CATEGORY")
	String category;

	@Column(name = "DESCRIPTION")
	String description;
	
	@Column(name = "COURSEID")
	Long courseId;
	
	public TaskEntity() {
		this.id = (long) 0;
		this.name = "";
		this.dueDate = LocalDateTime.now();
		this.category = "";
		this.description = "";
		this.courseId = (long) 0;
	}
	
	public TaskEntity(Long id, String name, LocalDateTime dueDate, String category, String description,
			Long courseId) {
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.category = category;
		this.description = description;
		this.courseId = courseId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
