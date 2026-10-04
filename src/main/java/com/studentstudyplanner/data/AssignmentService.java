package com.studentstudyplanner.data;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.studentstudyplanner.data.entity.AssignmentEntity;
import com.studentstudyplanner.data.entity.CourseEntity;
import com.studentstudyplanner.data.mapper.AssignmentRowMapper;
import com.studentstudyplanner.data.repository.AssignmentsRepository;
import com.studentstudyplanner.model.AssignmentModel;

@Service
public class AssignmentService implements DataAccessInterface<AssignmentEntity> {
	private final AssignmentsRepository assignmentsRepository;
	@SuppressWarnings("unused")
	private final DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private final AssignmentRowMapper assignmentRowMapper;
	private final CoursesService coursesService;

	/**
	 * Non-Default constructor for constructor injection.
	 */
	public AssignmentService(AssignmentsRepository assignmentsRepository, DataSource dataSource,
			AssignmentRowMapper assignmentRowMapper, CoursesService coursesService) {
		this.assignmentsRepository = assignmentsRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		this.assignmentRowMapper = assignmentRowMapper;
		this.coursesService = coursesService;
	}

	/**
	 * CRUD: finder to return a single entity
	 */
	public AssignmentEntity findById(Long id) {
		try {
			return assignmentsRepository.findById(id).get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * CRUD: finder to return all entities
	 */
	public List<AssignmentEntity> findAll() {
		try {
			return assignmentsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<AssignmentEntity> findAllByCourseId(long courseId) {
		List<AssignmentEntity> assignments = new ArrayList<AssignmentEntity>();
		
		String sql = "SELECT * FROM assignments WHERE courseId = ?";

		try {
			assignments = jdbcTemplateObject.query(sql, assignmentRowMapper, courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the List
		return assignments;
	}

	/**
	 * CRUD: create an entity
	 */
	public void create(AssignmentEntity assignment) {
		String sql = "INSERT INTO ASSIGNMENTS(NAME, CATEGORY, DUEDATE, FINISHED, COURSEID) VALUES(?, ?, ?, ?, ?)";
		try {
			// Execute SQL Insert
			jdbcTemplateObject.update(sql, assignment.getName(), assignment.getCategory(),
					assignment.getDueDate(), assignment.isFinished(), assignment.getCourseId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(AssignmentEntity assignment) {
		try {
			assignmentsRepository.save(assignment);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void delete(AssignmentEntity assignment) {
		try {
			assignmentsRepository.delete(assignment);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public AssignmentEntity ModelToEntity(AssignmentModel model) {
		List<CourseEntity> coursesResults = coursesService.findByName(model.getCourseName());
		
		if (coursesResults.isEmpty())
			return null;
		
		Long courseId = coursesResults.get(0).getId();
		
		return new AssignmentEntity(model.getId(), model.getName(), model.getCategory(), model.getDueDate(),
				model.isFinished(), courseId);
	}
	
	public AssignmentModel EntityToModel(AssignmentEntity entity)
	{
		CourseEntity ce = coursesService.findById(entity.getCourseId());
		if (ce == null)
			return null;
		
		return new AssignmentModel(entity.getId(), entity.getName(), entity.getCategory(), entity.getDueDate(),
				entity.isFinished(), ce.getCode());
	}
}
