
package com.studentstudyplanner.data;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.entity.NoteEntity;
import com.studentstudyplanner.data.mapper.NoteRowMapper;
import com.studentstudyplanner.data.repository.NotesRepository;
import com.studentstudyplanner.model.NoteModel;

/**
 * Provides database operations for managing notes
 * in the Student Study Planner application.
 *
 * This service allows notes to be created, retrieved,
 * updated, and deleted. It also converts note information
 * between NoteEntity and NoteModel objects.
 */
@Service
public class NotesService implements DataAccessInterface<NoteEntity> {

    private final NotesRepository notesRepository;

    @SuppressWarnings("unused")
    private final DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;
    private final NoteRowMapper noteRowMapper;
    private final CoursesService coursesService;

    /**
     * Creates a NotesService instance and initializes
     * the database tools and required services.
     *
     * @param notesRepository repository used to manage notes
     * @param dataSource database connection source
     * @param noteRowMapper mapper for note database records
     * @param coursesService service used to retrieve course information
     */
    public NotesService(
            NotesRepository notesRepository,
            DataSource dataSource,
            NoteRowMapper noteRowMapper,
            CoursesService coursesService) {

        this.notesRepository = notesRepository;
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.noteRowMapper = noteRowMapper;
        this.coursesService = coursesService;
    }

    /**
     * Finds a note using its unique database ID.
     *
     * @param id ID of the note to find
     * @return the matching note entity, or null
     *         if the note cannot be found or an error occurs
     */
    public NoteEntity findById(Long id) {
        NoteEntity result;

        try {
            result = notesRepository.findById(id).get();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retrieves all notes stored in the database.
     *
     * @return a list of all notes, or an empty list
     *         if no notes are found or an error occurs
     */
    public List<NoteEntity> findAll() {
        List<NoteEntity> notes = new ArrayList<NoteEntity>();

        try {
            Iterable<NoteEntity> notesIterable =
                notesRepository.findAll();

            notes = new ArrayList<NoteEntity>();
            notesIterable.forEach(notes::add);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the List
        return notes;
    }

    /**
     * Retrieves all notes associated with a specific course.
     *
     * Uses a SQL SELECT statement to find notes
     * belonging to the provided course ID.
     *
     * @param courseId ID of the course whose notes
     *                 should be retrieved
     * @return a list of notes associated with the course,
     *         or an empty list if none are found or an error occurs
     */
    public List<NoteEntity> findAllByCourseId(long courseId) {

        List<NoteEntity> notes = new ArrayList<NoteEntity>();

        String sql =
            "SELECT * FROM notes WHERE courseId = ?";

        try {
            notes = jdbcTemplateObject.query(
                sql, noteRowMapper, courseId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the List
        return notes;
    }

    /**
     * Creates a new note in the MySQL database.
     *
     * Saves the note title, category, content,
     * and associated course ID.
     *
     * @param note note entity containing
     *             the information to save
     */
    public void create(NoteEntity note) {

        String sql =
            "INSERT INTO NOTES(TITLE, CATEGORY, CONTENT, COURSEID) "
            + "VALUES(?, ?, ?, ?)";

        try {
            // Execute SQL Insert
            jdbcTemplateObject.update(
                sql,
                note.getTitle(),
                note.getCategory(),
                note.getContent(),
                note.getCourseId()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing note in the database.
     *
     * Uses the NotesRepository to save
     * the updated note information.
     *
     * @param note note entity containing
     *             the updated information
     */
    @Override
    public void update(NoteEntity note) {
        try {
            notesRepository.save(note);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes an existing note from the database.
     *
     * Uses the NotesRepository to remove
     * the specified note record.
     *
     * @param note note entity to delete
     */
    @Override
    public void delete(NoteEntity note) {
        try {
            notesRepository.delete(note);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Converts a NoteModel object into a NoteEntity object.
     *
     * Finds the course associated with the note
     * using the course name and current user's ID.
     *
     * @param model note model to convert
     * @param userId ID of the user who owns the course
     * @return a NoteEntity containing the note information,
     *         or null if no matching course is found
     */
    public NoteEntity ModelToEntity(NoteModel model, Long userId) {

        List<CourseEntity> coursesResults =
            coursesService.findByNameAndUserId(
                model.getCourseName(), userId);

        if (coursesResults.isEmpty())
            return null;

        Long courseId = coursesResults.get(0).getId();

        return new NoteEntity(
            model.getId(),
            model.getTitle(),
            model.getCategory(),
            model.getContent(),
            courseId
        );
    }

    /**
     * Converts a NoteEntity object into a NoteModel object.
     *
     * Retrieves the associated course so its name
     * can be included in the note model.
     *
     * @param entity note entity to convert
     * @return a NoteModel containing the note information,
     *         or null if the associated course cannot be found
     */
    public NoteModel EntityToModel(NoteEntity entity) {

        CourseEntity ce =
            coursesService.findById(entity.getCourseId());

        if (ce == null)
            return null;

        return new NoteModel(
            entity.getId(),
            entity.getTitle(),
            entity.getCategory(),
            entity.getContent(),
            ce.getName()
        );
    }
}

