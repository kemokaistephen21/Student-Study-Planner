package com.studentstudyplanner.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

public class TaskModel {
	private Long id;
	
	@NotBlank(message="Name is required")
	private String name;
	
	@NotNull(message="Due Date is required")
	private LocalDateTime dueDate;
	
	@NotBlank(message="Category is required")
	private String category;
	
	@NotBlank(message="Description is required")
	private String description;
	
	@NotBlank(message="Course name is required")
	private String courseName;

	public TaskModel(Long id, @NotBlank(message = "Name is required") String name,
			@NotNull(message = "Due Date is required") LocalDateTime dueDate,
			@NotBlank(message = "Category is required") String category,
			@NotBlank(message = "Description is required") String description,
			@NotBlank(message = "Course name is required") String courseName) {
		super();
		this.id = id;
		this.name = name;
		this.dueDate = dueDate;
		this.category = category;
		this.description = description;
		this.courseName = courseName;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
