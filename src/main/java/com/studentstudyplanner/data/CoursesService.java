
package com.studentstudyplanner.data;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.mapper.CourseRowMapper;
import com.studentstudyplanner.data.repository.CoursesRepository;
import com.studentstudyplanner.model.CourseModel;

/**
 * Provides database operations for managing courses
 * in the Student Study Planner application.
 *
 * This service allows courses to be created, retrieved,
 * updated, and deleted. It also converts course information
 * between CourseEntity and CourseModel objects.
 */
@Service
public class CoursesService implements DataAccessInterface<CourseEntity> {

    @Autowired
    private CoursesRepository coursesRepository;

    @SuppressWarnings("unused")
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;
    private CourseRowMapper courseRowMapper;

    /**
     * Creates a CoursesService instance and initializes
     * the database connection tools.
     *
     * @param coursesRepository repository used to manage courses
     * @param dataSource database connection source
     * @param courseRowMapper mapper provided for course records
     */
    public CoursesService(
            CoursesRepository coursesRepository,
            DataSource dataSource,
            CourseRowMapper courseRowMapper) {

        this.coursesRepository = coursesRepository;
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.courseRowMapper = new CourseRowMapper();
    }

    /**
     * Finds a course using its unique database ID.
     *
     * @param id ID of the course to find
     * @return the matching course entity, or null if
     *         the course cannot be found
     */
    public CourseEntity findById(Long id) {
        try {
            return coursesRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds courses that match a specific name
     * and belong to a specific user.
     *
     * @param name name of the course to search for
     * @param userId ID of the user who owns the course
     * @return a list of matching courses, or null
     *         if a database error occurs
     */
    public List<CourseEntity> findByNameAndUserId(
            String name, Long userId) {

        try {
            String sql =
                "SELECT * FROM courses WHERE name = ? AND userId = ?";

            return jdbcTemplateObject.query(
                sql, courseRowMapper, name, userId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all courses stored in the database.
     *
     * @return a list of all courses, or null if
     *         a database error occurs
     */
    public List<CourseEntity> findAll() {
        try {
            return coursesRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all courses belonging to a specific user.
     *
     * @param userId ID of the user whose courses
     *               should be retrieved
     * @return a list of the user's courses, or null
     *         if a database error occurs
     */
    public List<CourseEntity> findAllByUserId(long userId) {

        String sql =
            "SELECT * FROM courses WHERE userId = ?";

        try {
            return jdbcTemplateObject.query(
                sql, courseRowMapper, userId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a new course in the MySQL database.
     *
     * Uses an SQL INSERT statement to save the course
     * name, code, instructor, and user ID.
     *
     * @param course course entity containing
     *               the information to save
     */
    public void create(CourseEntity course) {

        String sql =
            "INSERT INTO COURSES(NAME, CODE, INSTRUCTOR, USERID) "
            + "VALUES(?, ?, ?, ?)";

        try {
            // Execute SQL Insert
            jdbcTemplateObject.update(
                sql,
                course.getName(),
                course.getCode(),
                course.getInstructor(),
                course.getUserId()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing course in the database.
     *
     * Uses the CoursesRepository to save
     * the updated course information.
     *
     * @param course course entity containing
     *               the updated information
     */
    @Override
    public void update(CourseEntity course) {
        try {
            coursesRepository.save(course);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes an existing course from the database.
     *
     * Uses the CoursesRepository to remove
     * the specified course record.
     *
     * @param course course entity to delete
     */
    @Override
    public void delete(CourseEntity course) {
        try {
            coursesRepository.delete(course);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Converts a CourseModel object into
     * a CourseEntity object.
     *
     * This allows course information from the
     * application to be used for database operations.
     *
     * @param model course model to convert
     * @return a CourseEntity containing
     *         the model's information
     */
    public CourseEntity ModelToEntity(CourseModel model) {

        return new CourseEntity(
            model.getId(),
            model.getName(),
            model.getCode(),
            model.getInstructor(),
            model.getUserId()
        );
    }

    /**
     * Converts a CourseEntity object into
     * a CourseModel object.
     *
     * This allows course information from the
     * database to be displayed in the application.
     *
     * @param entity course entity to convert
     * @return a CourseModel containing
     *         the entity's information
     */
    public CourseModel EntityToModel(CourseEntity entity) 
	{

        return new CourseModel(
            entity.getId(),
            entity.getName(),
            entity.getCode(),
            entity.getInstructor(),
            entity.getUserId()
        );
    }
}
