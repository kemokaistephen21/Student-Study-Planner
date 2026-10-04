package com.studentstudyplanner.model;

import jakarta.validation.constraints.*;

public class CourseModel {
	private Long id;
	
	@NotBlank(message="Course name is required")
	private String name;
	
	@NotBlank(message="Code is required")
	private String code;
	
	@NotBlank(message="Instructor is required")
	private String instructor;
	
	private Long userId;
	
	public CourseModel(Long id, @NotBlank(message = "Course name is required") String name,
			@NotBlank(message = "Code is required") String code,
			@NotBlank(message = "Instructor is required") String instructor,
			Long userId) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.instructor = instructor;
		this.userId = userId;
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
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}