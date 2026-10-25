
package com.studentstudyplanner.model;

import jakarta.validation.constraints.*;

/**
 * Represents note information used in the
 * Student Study Planner application.
 *
 * This model stores a note's title, category,
 * content, and associated course name.
 * Validation annotations identify required fields.
 */
public class NoteModel {

    /**
     * Unique ID used to identify the note.
     */
    private Long id;

    /**
     * Title of the note.
     * This field cannot be blank.
     */
    @NotBlank(message="Title is required")
    private String title;

    /**
     * Category used to organize the note.
     * This field cannot be blank.
     */
    @NotBlank(message="Category is required")
    private String category;

    /**
     * Content or information stored in the note.
     * This field cannot be blank.
     */
    @NotBlank(message="Content is required")
    private String content;

    /**
     * Name of the course associated with the note.
     * This field cannot be blank.
     */
    @NotBlank(message="Course name is required")
    private String courseName;

    /**
     * Creates a NoteModel with the provided note information.
     *
     * @param id unique ID of the note
     * @param title title of the note
     * @param category category of the note
     * @param content information stored in the note
     * @param courseName name of the associated course
     */
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

    /**
     * Creates an empty NoteModel with default values.
     *
     * The title, category, content, and course name
     * are initialized as empty strings.
     */
    public NoteModel() {
        this.title = "";
        this.category = "";
        this.content = "";
        this.courseName = "";
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
     * Retrieves the name of the course associated
     * with the note.
     *
     * @return the associated course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course associated
     * with the note.
     *
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

