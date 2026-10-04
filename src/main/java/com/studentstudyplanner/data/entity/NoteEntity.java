package com.studentstudyplanner.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NOTES")
public class NoteEntity {
	@Id
	Long id;

	@Column(name = "TITLE")
	String title;

	@Column(name = "CATEGORY")
	String category;
	
	@Column(name = "CONTENT")
	String content;

	@Column(name = "COURSEID")
	Long courseId;
	
	public NoteEntity() {
		this.id = (long) 0;
		this.title = "";
		this.category = "";
		this.content = "";
		this.courseId = (long) 0;
	}
	
	public NoteEntity(Long id, String title, String category, String content, Long courseId) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.content = content;
		this.courseId = courseId;
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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
}
