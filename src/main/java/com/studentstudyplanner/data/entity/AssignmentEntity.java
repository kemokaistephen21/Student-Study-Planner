package com.studentstudyplanner.data.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ASSIGNMENTS")
public class AssignmentEntity {
	@Id
	Long id;

	@Column(name = "NAME")
	String name;

	@Column(name = "CATEGORY")
	String category;

	@Column(name = "DUEDATE")
	LocalDateTime dueDate;
	
	@Column(name = "FINISHED")
	boolean finished;

	@Column(name = "COURSEID")
	Long courseId;
	
	public AssignmentEntity() {
		this.id = (long) 0;
		this.name = "";
		this.category = "";
		this.dueDate = LocalDateTime.now();
		this.finished = false;
		this.courseId = (long) 0;
	}
	
	public AssignmentEntity(Long id, String name, String category, LocalDateTime dueDate,
			boolean finished, Long courseId) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.dueDate = dueDate;
		this.finished = finished;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
