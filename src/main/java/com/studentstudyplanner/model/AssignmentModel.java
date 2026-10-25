
package com.studentstudyplanner.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;

/**
 * Represents assignment information used in the
 * Student Study Planner application.
 *
 * This model stores an assignment's name, category,
 * due date, completion status, and associated course name.
 * It also includes validation annotations for required fields.
 */
public class AssignmentModel {

    /**
     * Unique ID used to identify the assignment.
     */
    private Long id;

    /**
     * Name of the assignment.
     * This field cannot be blank.
     */
    @NotBlank(message="Name is required")
    private String name;

    /**
     * Category used to organize the assignment.
     * This field cannot be blank.
     */
    @NotBlank(message="Category is required")
    private String category;

    /**
     * Date when the assignment is due.
     * The date is formatted as year-month-day.
     */
    @NotNull(message="Due date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    /**
     * Indicates whether the assignment has been completed.
     */
    @NotNull(message="Finished state is required")
    boolean finished;

    /**
     * Name of the course associated with the assignment.
     * This field cannot be blank.
     */
    @NotBlank(message="Course Name is required")
    String courseName;

    /**
     * Creates an AssignmentModel with the provided information.
     *
     * @param id unique ID of the assignment
     * @param name name of the assignment
     * @param category category of the assignment
     * @param dueDate date when the assignment is due
     * @param finished whether the assignment is completed
     * @param courseName name of the associated course
     */
    public AssignmentModel(Long id, @NotBlank(message = "Name is required") String name,
            @NotBlank(message = "Category is required") String category,
            @NotNull(message = "Due date is required") LocalDate dueDate,
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

    /**
     * Creates an empty AssignmentModel with default values.
     *
     * The name, category, and course name are initialized
     * as empty strings. The due date is set to the current
     * date, and the completion status is set to false.
     */
    public AssignmentModel() {
        this.name = "";
        this.category = "";
        this.dueDate = LocalDate.now();
        this.finished = false;
        this.courseName = "";
    }

    /**
     * Retrieves the unique ID of the assignment.
     *
     * @return the assignment ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID of the assignment.
     *
     * @param id the assignment ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the assignment.
     *
     * @return the assignment name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the assignment.
     *
     * @param name the assignment name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the category of the assignment.
     *
     * @return the assignment category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the assignment.
     *
     * @param category the assignment category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retrieves the due date of the assignment.
     *
     * @return the assignment due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the assignment.
     *
     * @param dueDate the due date to set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Checks whether the assignment has been completed.
     *
     * @return true if the assignment is completed,
     *         otherwise false
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Sets the completion status of the assignment.
     *
     * @param finished true if the assignment is completed,
     *                 otherwise false
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Retrieves the name of the course associated
     * with the assignment.
     *
     * @return the associated course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course associated
     * with the assignment.
     *
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
