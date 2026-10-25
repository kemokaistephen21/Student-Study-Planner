
package com.studentstudyplanner.model;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

/**
 * Represents a study task in the Student Study Planner application.
 *
 * This model stores information about a study task, including
 * its name, due date, category, description, and associated
 * course name. Validation annotations identify required fields.
 */
public class TaskModel {

    /**
     * Unique ID used to identify the study task.
     */
    private Long id;

    /**
     * Name of the study task.
     * This field cannot be blank.
     */
    @NotBlank(message="Name is required")
    private String name;

    /**
     * Date when the study task is due.
     * This field cannot be null.
     */
    @NotNull(message="Due Date is required")
    private LocalDate dueDate;

    /**
     * Category used to organize the study task.
     * This field cannot be blank.
     */
    @NotBlank(message="Category is required")
    private String category;

    /**
     * Description containing details about the study task.
     * This field cannot be blank.
     */
    @NotBlank(message="Description is required")
    private String description;

    /**
     * Name of the course associated with the study task.
     * This field cannot be blank.
     */
    @NotBlank(message="Course name is required")
    private String courseName;

    /**
     * Creates a TaskModel with the provided study task information.
     *
     * @param id unique ID of the study task
     * @param name name of the study task
     * @param dueDate date when the study task is due
     * @param category category of the study task
     * @param description details about the study task
     * @param courseName name of the associated course
     */
    public TaskModel(Long id, @NotBlank(message = "Name is required") String name,
            @NotNull(message = "Due Date is required") LocalDate dueDate,
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

    /**
     * Creates an empty TaskModel with default values.
     *
     * The name, category, and course name are initialized
     * as empty strings, and the due date is set to the
     * current date. The description is not initialized here.
     */
    public TaskModel() {
        this.name = "";
        this.dueDate = LocalDate.now();
        this.category = "";
        this.courseName = "";
    }

    /**
     * Retrieves the unique ID of the study task.
     *
     * @return the task ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID of the study task.
     *
     * @param id the task ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the study task.
     *
     * @return the task name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the study task.
     *
     * @param name the task name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the due date of the study task.
     *
     * @return the task due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the study task.
     *
     * @param dueDate the due date to set
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Retrieves the category of the study task.
     *
     * @return the task category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the study task.
     *
     * @param category the task category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retrieves the description of the study task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the study task.
     *
     * @param description the task description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the name of the course associated
     * with the study task.
     *
     * @return the associated course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course associated
     * with the study task.
     *
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

