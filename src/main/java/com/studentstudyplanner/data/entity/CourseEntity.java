package com.studentstudyplanner.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COURSES")
public class CourseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "NAME")
	String name;
	
	@Column(name = "CODE")
	String code;

	@Column(name = "INSTRUCTOR")
	String instructor;

	@Column(name = "USERID")
	Long userId;
	
	public CourseEntity() {
		this.id = (long) 0;
		this.name = "";
		this.code = "";
		this.instructor = "";
		this.userId = (long) 0;
	}
	
	public CourseEntity(Long id, String name, String code, String instructor, Long userId) {
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

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
