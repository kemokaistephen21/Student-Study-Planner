
package com.studentstudyplanner.model;

import jakarta.validation.constraints.*;

/**
 * Represents course information used in the Student Study Planner application.
 *
 * This model stores the course ID, name, code, instructor,
 * and associated user ID. It also uses validation annotations
 * to make sure required course information is provided.
 */
public class CourseModel {

    /**
     * Unique ID used to identify the course.
     */
    private Long id;

    /**
     * Name of the course.
     * This field cannot be blank.
     */
    @NotBlank(message="Course name is required")
    private String name;

    /**
     * Course code used to identify the class.
     * This field cannot be blank.
     */
    @NotBlank(message="Code is required")
    private String code;

    /**
     * Name of the instructor teaching the course.
     * This field cannot be blank.
     */
    @NotBlank(message="Instructor is required")
    private String instructor;

    /**
     * ID of the user associated with the course.
     */
    private Long userId;

    /**
     * Creates a CourseModel with the provided course information.
     *
     * @param id unique ID of the course
     * @param name name of the course
     * @param code course code
     * @param instructor name of the course instructor
     * @param userId ID of the user associated with the course
     */
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

    /**
     * Creates an empty CourseModel with default values.
     *
     * The course name, code, and instructor are initialized
     * as empty strings, while the user ID is set to zero.
     */
    public CourseModel() {
        this.name = "";
        this.code = "";
        this.instructor = "";
        this.userId = 0L;
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
     * Sets the name of the course.
     *
     * @param name the course name to set
     */
    public void setName(String name) {
        this.name = name;
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
