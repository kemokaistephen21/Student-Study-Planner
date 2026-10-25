
package com.studentstudyplanner.data;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.studentstudyplanner.data.entity.AssignmentEntity;
import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.mapper.AssignmentRowMapper;
import com.studentstudyplanner.data.repository.AssignmentsRepository;
import com.studentstudyplanner.model.AssignmentModel;

/**
 * Provides database operations for managing assignments
 * in the Student Study Planner application.
 *
 * This service allows assignments to be created, retrieved,
 * updated, and deleted. It also converts assignment data
 * between AssignmentEntity and AssignmentModel objects.
 */
@Service
public class AssignmentService implements DataAccessInterface<AssignmentEntity> {

    private final AssignmentsRepository assignmentsRepository;

    @SuppressWarnings("unused")
    private final DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;
    private final AssignmentRowMapper assignmentRowMapper;
    private final CoursesService coursesService;

    /**
     * Creates an AssignmentService instance and initializes
     * the database tools and required services.
     *
     * @param assignmentsRepository repository for managing assignments
     * @param dataSource database connection source
     * @param assignmentRowMapper mapper for assignment database records
     * @param coursesService service for retrieving course information
     */
    public AssignmentService(
            AssignmentsRepository assignmentsRepository,
            DataSource dataSource,
            AssignmentRowMapper assignmentRowMapper,
            CoursesService coursesService) {

        this.assignmentsRepository = assignmentsRepository;
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.assignmentRowMapper = assignmentRowMapper;
        this.coursesService = coursesService;
    }

    /**
     * Finds an assignment using its unique database ID.
     *
     * @param id ID of the assignment to find
     * @return the matching assignment entity, or null
     *         if the assignment cannot be found
     */
    public AssignmentEntity findById(Long id) {
        try {
            return assignmentsRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all assignments stored in the database.
     *
     * @return a list of all assignments, or null
     *         if a database error occurs
     */
    public List<AssignmentEntity> findAll() {
        try {
            return assignmentsRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all assignments associated with a specific course.
     *
     * Uses a SQL SELECT statement to find assignments
     * that belong to the provided course ID.
     *
     * @param courseId ID of the course whose assignments
     *                 should be retrieved
     * @return a list of assignments associated with the course
     */
    public List<AssignmentEntity> findAllByCourseId(long courseId) {

        List<AssignmentEntity> assignments =
            new ArrayList<AssignmentEntity>();

        String sql =
            "SELECT * FROM assignments WHERE courseId = ?";

        try {
            assignments = jdbcTemplateObject.query(
                sql, assignmentRowMapper, courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the List
        return assignments;
    }

    /**
     * Creates a new assignment in the MySQL database.
     *
     * Saves the assignment name, category, due date,
     * completion status, and associated course ID.
     *
     * @param assignment assignment entity containing
     *                   the information to save
     */
    public void create(AssignmentEntity assignment) {

        String sql =
            "INSERT INTO ASSIGNMENTS(NAME, CATEGORY, DUEDATE, "
            + "FINISHED, COURSEID) VALUES(?, ?, ?, ?, ?)";

        try {
            // Execute SQL Insert
            jdbcTemplateObject.update(
                sql,
                assignment.getName(),
                assignment.getCategory(),
                assignment.getDueDate(),
                assignment.isFinished(),
                assignment.getCourseId()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing assignment in the database.
     *
     * Uses the AssignmentsRepository to save
     * the updated assignment information.
     *
     * @param assignment assignment entity containing
     *                   the updated information
     */
    @Override
    public void update(AssignmentEntity assignment) {
        try {
            assignmentsRepository.save(assignment);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes an existing assignment from the database.
     *
     * Uses the AssignmentsRepository to remove
     * the specified assignment record.
     *
     * @param assignment assignment entity to delete
     */
    @Override
    public void delete(AssignmentEntity assignment) {
        try {
            assignmentsRepository.delete(assignment);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Converts an AssignmentModel object into
     * an AssignmentEntity object.
     *
     * Finds the course associated with the assignment
     * using the course name and current user's ID.
     *
     * @param model assignment model to convert
     * @param userId ID of the user who owns the course
     * @return an AssignmentEntity containing the assignment
     *         information, or null if no matching course exists
     */
    public AssignmentEntity ModelToEntity(
            AssignmentModel model, Long userId) {

        List<CourseEntity> coursesResults =
            coursesService.findByNameAndUserId(
                model.getCourseName(), userId);

        if (coursesResults.isEmpty())
            return null;

        Long courseId = coursesResults.get(0).getId();

        return new AssignmentEntity(
            model.getId(),
            model.getName(),
            model.getCategory(),
            model.getDueDate(),
            model.isFinished(),
            courseId
        );
    }

    /**
     * Converts an AssignmentEntity object into
     * an AssignmentModel object.
     *
     * Retrieves the associated course so its name
     * can be included in the assignment model.
     *
     * @param entity assignment entity to convert
     * @return an AssignmentModel containing the assignment
     *         information, or null if the course is not found
     */
    public AssignmentModel EntityToModel(AssignmentEntity entity) {

        CourseEntity ce =
            coursesService.findById(entity.getCourseId());

        if (ce == null)
            return null;

        return new AssignmentModel(
            entity.getId(),
            entity.getName(),
            entity.getCategory(),
            entity.getDueDate(),
            entity.isFinished(),
            ce.getName()
        );
    }
}
