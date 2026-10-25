
package com.studentstudyplanner.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a course stored in the Student Study Planner database.
 *
 * This entity contains information about a course, including
 * its name, course code, instructor, and associated user ID.
 */
@Entity
@Table(name = "COURSES")
public class CourseEntity {

    /**
     * Unique ID used to identify the course in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Name of the course.
     */
    @Column(name = "NAME")
    String name;

    /**
     * Course code used to identify the class.
     */
    @Column(name = "CODE")
    String code;

    /**
     * Name of the instructor teaching the course.
     */
    @Column(name = "INSTRUCTOR")
    String instructor;

    /**
     * ID of the user who owns the course.
     */
    @Column(name = "USERID")
    Long userId;

    /**
     * Creates an empty CourseEntity with default values.
     */
    public CourseEntity() {
        this.id = (long) 0;
        this.name = "";
        this.code = "";
        this.instructor = "";
        this.userId = (long) 0;
    }

    /**
     * Creates a CourseEntity with the provided course information.
     *
     * @param id unique ID of the course
     * @param name name of the course
     * @param code course code
     * @param instructor name of the course instructor
     * @param userId ID of the user who owns the course
     */
    public CourseEntity(Long id, String name, String code,
                        String instructor, Long userId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.userId = userId;
    }

    /**
     * Retrieves the unique ID of the course.
     *
     * @return the course ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID of the course.
     *
     * @param id the course ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the course.
     *
     * @return the course name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name the course name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the course code.
     *
     * @return the course code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the course code.
     *
     * @param code the course code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves the name of the course instructor.
     *
     * @return the instructor's name
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets the name of the course instructor.
     *
     * @param instructor the instructor's name to set
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Retrieves the ID of the user associated with the course.
     *
     * @return the user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user associated with the course.
     *
     * @param userId the user ID to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

