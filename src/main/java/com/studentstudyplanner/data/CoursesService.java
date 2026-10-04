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

@Service
public class CoursesService implements DataAccessInterface<CourseEntity> {
	@Autowired
	private CoursesRepository coursesRepository;
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private CourseRowMapper courseRowMapper;

	/**
	 * Non-Default constructor for constructor injection.
	 */
	public CoursesService(CoursesRepository coursesRepository, DataSource dataSource, CourseRowMapper courseRowMapper) {
		this.coursesRepository = coursesRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		this.courseRowMapper = new CourseRowMapper();
	}

	/**
	 * CRUD: finder to return a single entity
	 */
	public CourseEntity findById(Long id) {
		try {
			return coursesRepository.findById(id).get();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<CourseEntity> findByName(String name) {
		try {
			String sql = "SELECT * FROM courses WHERE name = ?";
			return jdbcTemplateObject.query(sql, courseRowMapper, name);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * CRUD: finder to return all entities
	 */
	public List<CourseEntity> findAll() {
		try {
			return coursesRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<CourseEntity> findAllByUserId(long userId) {
		List<CourseEntity> courses = new ArrayList<CourseEntity>();
		
		String sql = "SELECT * FROM courses WHERE userId = ?";

		try {
			return jdbcTemplateObject.query(sql, courseRowMapper, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * CRUD: create an entity
	 */
	public void create(CourseEntity course) {
		String sql = "INSERT INTO COURSES(NAME, CODE, INSTRUCTOR, USERID) VALUES(?, ?, ?, ?)";
		try {
			// Execute SQL Insert
			jdbcTemplateObject.update(sql, course.getName(), course.getCode(),
					course.getInstructor(), course.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CourseEntity course) {
		try {
			coursesRepository.save(course);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void delete(CourseEntity course) {
		try {
			coursesRepository.delete(course);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public CourseEntity ModelToEntity(CourseModel model) {
		return new CourseEntity(model.getId(), model.getName(), model.getCode(), model.getInstructor(),
				model.getUserId());
	}
	
	public CourseModel EntityToModel(CourseEntity entity) {
		return new CourseModel(entity.getId(), entity.getName(), entity.getCode(), entity.getInstructor(),
				entity.getUserId());
	}
}