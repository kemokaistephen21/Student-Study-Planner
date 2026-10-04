package com.studentstudyplanner.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

public class AssignmentModel {
	private Long id;
	
	@NotBlank(message="Name is required")
	private String name;
	
	@NotBlank(message="Category is required")
	private String category;
	
	@NotNull(message="Due date is required")
	private LocalDateTime dueDate;
	
	@NotNull(message="Finished state is required")
	boolean finished;
	
	@NotBlank(message="Course Name is required")
	String courseName;
	
	public AssignmentModel(Long id, @NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Category is required") String category,
			@NotNull(message = "Due date is required") LocalDateTime dueDate,
			@NotNull(message = "Finished state is required") boolean finished,
			@NotBlank(message = "Course Name is required") String courseName) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.dueDate = dueDate;
		this.finished = finished;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
