package com.studentstudyplanner.data;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.entity.TaskEntity;
import com.studentstudyplanner.data.mapper.TaskRowMapper;
import com.studentstudyplanner.data.repository.TasksRepository;
import com.studentstudyplanner.model.TaskModel;

@Service
public class TasksService implements DataAccessInterface<TaskEntity> {
	private final TasksRepository tasksRepository;
	@SuppressWarnings("unused")
	private final DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private final TaskRowMapper taskRowMapper;
	private final CoursesService coursesService;

	/**
	 * Non-Default constructor for constructor injection.
	 */
	public TasksService(TasksRepository tasksRepository, DataSource dataSource, TaskRowMapper taskRowMapper,
			CoursesService coursesService) {
		this.tasksRepository = tasksRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		this.taskRowMapper = new TaskRowMapper();
		this.coursesService = coursesService;
	}

	/**
	 * CRUD: finder to return a single entity
	 */
	public TaskEntity findById(Long id) {
		TaskEntity result;
		try {
			result = tasksRepository.findById(id).get();
			return result;
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * CRUD: finder to return all entities
	 */
	public List<TaskEntity> findAll() {
		List<TaskEntity> tasks = new ArrayList<TaskEntity>();

		try {
			Iterable<TaskEntity> tasksIterable = tasksRepository.findAll();
			tasks = new ArrayList<TaskEntity>();
			tasksIterable.forEach(tasks::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the List
		return tasks;
	}
	
	public List<TaskEntity> findAllByCourseId(long courseId) {
		List<TaskEntity> tasks = new ArrayList<TaskEntity>();
		
		String sql = "SELECT * FROM tasks WHERE courseId = ?";

		try {
			jdbcTemplateObject.query(sql, taskRowMapper, courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the List
		return tasks;
	}

	/**
	 * CRUD: create an entity
	 */
	public void create(TaskEntity task) {
		String sql = "INSERT INTO TASKS(NAME, DUEDATE, CATEGORY, DESCRIPTION, COURSEID) VALUES(?, ?, ?, ?, ?)";
		try {
			// Execute SQL Insert
			jdbcTemplateObject.update(sql, task.getName(), task.getDueDate(), task.getCategory(),
					task.getDescription(), task.getCourseId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TaskEntity task) {
		try {
			tasksRepository.save(task);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void delete(TaskEntity task) {
		try {
			tasksRepository.delete(task);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public TaskEntity ModelToEntity(TaskModel model) {
		List<CourseEntity> coursesResults = coursesService.findByName(model.getCourseName());
		
		if (coursesResults.isEmpty())
			return null;
		
		Long courseId = coursesResults.get(0).getId();
		
		return new TaskEntity(model.getId(), model.getName(), model.getDueDate(), model.getCategory(),
				model.getDescription(), courseId);
	}
	
	public TaskModel EntityToModel(TaskEntity entity)
	{
		CourseEntity ce = coursesService.findById(entity.getCourseId());
		if (ce == null)
			return null;
		
		return new TaskModel(entity.getId(), entity.getName(), entity.getDueDate(), entity.getCategory(),
				entity.getDescription(), ce.getCode());
	}
}
