
package com.studentstudyplanner.data.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents a study task stored in the Student Study Planner database.
 *
 * This entity contains information about a study task, including
 * its name, due date, category, description, and associated course ID.
 */
@Entity
@Table(name = "TASKS")
public class TaskEntity {

    /**
     * Unique ID used to identify the study task in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Name of the study task.
     */
    @Column(name = "NAME")
    String name;

    /**
     * Date when the study task is due.
     */
    @Column(name = "DUEDATE")
    LocalDate dueDate;

    /**
     * Category used to organize the study task.
     */
    @Column(name = "CATEGORY")
    String category;

    /**
     * Description containing additional details about the task.
     */
    @Column(name = "DESCRIPTION")
    String description;

    /**
     * ID of the course associated with the study task.
     */
    @Column(name = "COURSEID")
    Long courseId;

    /**
     * Creates an empty TaskEntity with default values.
     *
     * The due date is set to the current date, while
     * the remaining fields receive their default values.
     */
    public TaskEntity() {
        this.id = (long) 0;
        this.name = "";
        this.dueDate = LocalDate.now();
        this.category = "";
        this.description = "";
        this.courseId = (long) 0;
    }

    /**
     * Creates a TaskEntity with the provided study task information.
     *
     * @param id unique ID of the study task
     * @param name name of the study task
     * @param dueDate date when the study task is due
     * @param category category of the study task
     * @param description additional details about the study task
     * @param courseId ID of the course associated with the task
     */
    public TaskEntity(Long id, String name, LocalDate dueDate,
            String category, String description, Long courseId) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.category = category;
        this.description = description;
        this.courseId = courseId;
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
     * Retrieves the ID of the course associated with the task.
     *
     * @return the associated course ID
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Sets the ID of the course associated with the task.
     *
     * @param courseId the course ID to set
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
