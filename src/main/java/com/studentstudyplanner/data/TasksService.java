
package com.studentstudyplanner.data;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.entity.TaskEntity;
import com.studentstudyplanner.data.mapper.TaskRowMapper;
import com.studentstudyplanner.data.repository.TasksRepository;
import com.studentstudyplanner.model.TaskModel;

/**
 * Provides database operations for managing study tasks
 * in the Student Study Planner application.
 *
 * This service allows tasks to be created, retrieved,
 * updated, and deleted. It also converts task information
 * between TaskEntity and TaskModel objects.
 */
@Service
public class TasksService implements DataAccessInterface<TaskEntity> {

    private final TasksRepository tasksRepository;

    @SuppressWarnings("unused")
    private final DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;
    private final TaskRowMapper taskRowMapper;
    private final CoursesService coursesService;

    /**
     * Creates a TasksService instance and initializes
     * the database tools and required services.
     *
     * @param tasksRepository repository used to manage tasks
     * @param dataSource database connection source
     * @param taskRowMapper mapper provided for task records
     * @param coursesService service used to retrieve course information
     */
    public TasksService(
            TasksRepository tasksRepository,
            DataSource dataSource,
            TaskRowMapper taskRowMapper,
            CoursesService coursesService) {

        this.tasksRepository = tasksRepository;
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.taskRowMapper = new TaskRowMapper();
        this.coursesService = coursesService;
    }

    /**
     * Finds a study task using its unique database ID.
     *
     * @param id ID of the task to find
     * @return the matching task entity, or null
     *         if the task cannot be found
     */
    public TaskEntity findById(Long id) {
        TaskEntity result;

        try {
            result = tasksRepository.findById(id).get();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retrieves all study tasks stored in the database.
     *
     * @return a list of all tasks, or an empty list
     *         if no tasks are found or an error occurs
     */
    public List<TaskEntity> findAll() {
        List<TaskEntity> tasks = new ArrayList<TaskEntity>();

        try {
            Iterable<TaskEntity> tasksIterable =
                tasksRepository.findAll();

            tasks = new ArrayList<TaskEntity>();
            tasksIterable.forEach(tasks::add);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the List
        return tasks;
    }

    /**
     * Retrieves all study tasks associated with a specific course.
     *
     * Uses a SQL SELECT statement to find tasks
     * belonging to the provided course ID.
     *
     * @param courseId ID of the course whose tasks
     *                 should be retrieved
     * @return a list of tasks associated with the course,
     *         or an empty list if none are found or an error occurs
     */
    public List<TaskEntity> findAllByCourseId(long courseId) {

        List<TaskEntity> tasks = new ArrayList<TaskEntity>();

        String sql =
            "SELECT * FROM tasks WHERE courseId = ?";

        try {
            tasks = jdbcTemplateObject.query(
                sql, taskRowMapper, courseId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the List
        return tasks;
    }

    /**
     * Creates a new study task in the MySQL database.
     *
     * Saves the task name, due date, category,
     * description, and associated course ID.
     *
     * @param task task entity containing
     *             the information to save
     */
    public void create(TaskEntity task) {

        String sql =
            "INSERT INTO TASKS(NAME, DUEDATE, CATEGORY, "
            + "DESCRIPTION, COURSEID) VALUES(?, ?, ?, ?, ?)";

        try {
            // Execute SQL Insert
            jdbcTemplateObject.update(
                sql,
                task.getName(),
                task.getDueDate(),
                task.getCategory(),
                task.getDescription(),
                task.getCourseId()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing study task in the database.
     *
     * Uses the TasksRepository to save
     * the updated task information.
     *
     * @param task task entity containing
     *             the updated information
     */
    @Override
    public void update(TaskEntity task) {
        try {
            tasksRepository.save(task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes an existing study task from the database.
     *
     * Uses the TasksRepository to remove
     * the specified task record.
     *
     * @param task task entity to delete
     */
    @Override
    public void delete(TaskEntity task) {
        try {
            tasksRepository.delete(task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Converts a TaskModel object into a TaskEntity object.
     *
     * Finds the course associated with the task
     * using the course name and current user's ID.
     *
     * @param model task model to convert
     * @param userId ID of the user who owns the course
     * @return a TaskEntity containing the task information,
     *         or null if no matching course is found
     */
    public TaskEntity ModelToEntity(TaskModel model, Long userId) {

        List<CourseEntity> coursesResults =
            coursesService.findByNameAndUserId(
                model.getCourseName(), userId);

        if (coursesResults.isEmpty())
            return null;

        Long courseId = coursesResults.get(0).getId();

        return new TaskEntity(
            model.getId(),
            model.getName(),
            model.getDueDate(),
            model.getCategory(),
            model.getDescription(),
            courseId
        );
    }

    /**
     * Converts a TaskEntity object into a TaskModel object.
     *
     * Retrieves the associated course so its name
     * can be included in the task model.
     *
     * @param entity task entity to convert
     * @return a TaskModel containing the task information,
     *         or null if the associated course cannot be found
     */
    public TaskModel EntityToModel(TaskEntity entity) {

        CourseEntity ce =
            coursesService.findById(entity.getCourseId());

        if (ce == null)
            return null;

        return new TaskModel(
            entity.getId(),
            entity.getName(),
            entity.getDueDate(),
            entity.getCategory(),
            entity.getDescription(),
            ce.getName()
        );
    }
}

	}
}
