package com.studentstudyplanner.model;

import jakarta.validation.constraints.*;

public class NoteModel {
	private Long id;
	
	@NotBlank(message="Title is required")
	private String title;
	
	@NotBlank(message="Category is required")
	private String category;
	
	@NotBlank(message="Content is required")
	private String content;
	
	@NotBlank(message="Course name is required")
	private String courseName;

	public NoteModel(Long id, @NotBlank(message = "Title is required") String title,
			@NotBlank(message = "Category is required") String category,
			@NotBlank(message = "Content is required") String content,
			@NotBlank(message = "Course name is required") String courseName) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.content = content;
		this.courseName = courseName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
