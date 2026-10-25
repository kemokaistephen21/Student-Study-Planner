
package com.studentstudyplanner.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a note stored in the Student Study Planner database.
 *
 * This entity contains information about a note, including
 * its title, category, content, and associated course ID.
 */
@Entity
@Table(name = "NOTES")
public class NoteEntity {

    /**
     * Unique ID used to identify the note in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Title of the note.
     */
    @Column(name = "TITLE")
    String title;

    /**
     * Category used to organize the note.
     */
    @Column(name = "CATEGORY")
    String category;

    /**
     * Content or information stored in the note.
     */
    @Column(name = "CONTENT")
    String content;

    /**
     * ID of the course associated with the note.
     */
    @Column(name = "COURSEID")
    Long courseId;

    /**
     * Creates an empty NoteEntity with default values.
     *
     * The ID and course ID are initialized to zero,
     * while the text fields are initialized as empty strings.
     */
    public NoteEntity() {
        this.id = (long) 0;
        this.title = "";
        this.category = "";
        this.content = "";
        this.courseId = (long) 0;
    }

    /**
     * Creates a NoteEntity with the provided note information.
     *
     * @param id unique ID of the note
     * @param title title of the note
     * @param category category of the note
     * @param content information stored in the note
     * @param courseId ID of the associated course
     */
    public NoteEntity(Long id, String title, String category,
            String content, Long courseId) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.courseId = courseId;
    }

    /**
     * Retrieves the unique ID of the note.
     *
     * @return the note ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID of the note.
     *
     * @param id the note ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the title of the note.
     *
     * @return the note title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the note.
     *
     * @param title the note title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the category of the note.
     *
     * @return the note category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the note.
     *
     * @param category the note category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retrieves the content stored in the note.
     *
     * @return the note content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content stored in the note.
     *
     * @param content the note content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the ID of the course associated with the note.
     *
     * @return the associated course ID
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Sets the ID of the course associated with the note.
     *
     * @param courseId the course ID to set
     */
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
