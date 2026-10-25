
package com.studentstudyplanner.data.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an assignment stored in the Student Study Planner database.
 *
 * This entity contains information about an assignment, including
 * its name, category, due date, completion status, and associated course ID.
 */
@Entity
@Table(name = "ASSIGNMENTS")
public class AssignmentEntity {

    /**
     * Unique ID used to identify the assignment in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Name of the assignment.
     */
    @Column(name = "NAME")
    String name;

    /**
     * Category used to organize the assignment.
     */
    @Column(name = "CATEGORY")
    String category;

    /**
     * Date when the assignment is due.
     */
    @Column(name = "DUEDATE")
    LocalDate dueDate;

    /**
     * Indicates whether the assignment has been completed.
     */
    @Column(name = "FINISHED")
    boolean finished;

    /**
     * ID of the course associated with the assignment.
     */
    @Column(name = "COURSEID")
    Long courseId;

    /**
     * Creates an empty AssignmentEntity with default values.
     *
     * The due date is set to the current date, and
     * the completion status is set to false.
     */
    public AssignmentEntity() {
        this.id = (long) 0;
        this.name = "";
        this.category = "";
        this.dueDate = LocalDate.now();
        this.finished = false;
        this.courseId = (long) 0;
    }

    /**
     * Creates an AssignmentEntity with the provided assignment information.
     *
     * @param id unique ID of the assignment
     * @param name name of the assignment
     * @param category category of the assignment
     * @param dueDate date when the assignment is due
     * @param finished whether the assignment has been completed
     * @param courseId ID of the course associated with the assignment
     */
    public AssignmentEntity(Long id, String name, String category, LocalDate dueDate,
            boolean finished, Long courseId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.dueDate = dueDate;
        this.finished = finished;
        this.courseId = courseId;
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
     * Retrieves the ID of the course associated with the assignment.
     *
     * @return the associated course ID
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Sets the ID of the course associated with the assignment.
     *
     * @param courseId the course ID to set
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
