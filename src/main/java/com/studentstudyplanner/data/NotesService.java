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

@Service
public class NotesService implements DataAccessInterface<NoteEntity> {
	private final NotesRepository notesRepository;
	@SuppressWarnings("unused")
	private final DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private final NoteRowMapper noteRowMapper;
	private final CoursesService coursesService;

	/**
	 * Non-Default constructor for constructor injection.
	 */
	public NotesService(NotesRepository notesRepository, DataSource dataSource, NoteRowMapper noteRowMapper,
			CoursesService coursesService) {
		this.notesRepository = notesRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		this.noteRowMapper = noteRowMapper;
		this.coursesService = coursesService;
	}

	/**
	 * CRUD: finder to return a single entity
	 */
	public NoteEntity findById(Long id) {
		NoteEntity result;
		try {
			result = notesRepository.findById(id).get();
			return result;
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * CRUD: finder to return all entities
	 */
	public List<NoteEntity> findAll() {
		List<NoteEntity> notes = new ArrayList<NoteEntity>();

		try {
			Iterable<NoteEntity> notesIterable = notesRepository.findAll();
			notes = new ArrayList<NoteEntity>();
			notesIterable.forEach(notes::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the List
		return notes;
	}
	
	public List<NoteEntity> findAllByCourseId(long courseId) {
		List<NoteEntity> notes = new ArrayList<NoteEntity>();
		
		String sql = "SELECT * FROM notes WHERE courseId = ?";

		try {
			jdbcTemplateObject.query(sql, noteRowMapper, courseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Return the List
		return notes;
	}

	/**
	 * CRUD: create an entity
	 */
	public void create(NoteEntity note) {
		String sql = "INSERT INTO NOTES(TITLE, CATEGORY, CONTENT, COURSEID) VALUES(?, ?, ?, ?)";
		try {
			// Execute SQL Insert
			jdbcTemplateObject.update(sql, note.getTitle(), note.getCategory(),
					note.getContent(), note.getCourseId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(NoteEntity note) {
		try {
			notesRepository.save(note);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void delete(NoteEntity note) {
		try {
			notesRepository.delete(note);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public NoteEntity ModelToEntity(NoteModel model) {
		List<CourseEntity> coursesResults = coursesService.findByName(model.getCourseName());
		
		if (coursesResults.isEmpty())
			return null;
		
		Long courseId = coursesResults.get(0).getId();
		
		return new NoteEntity(model.getId(), model.getTitle(), model.getCategory(), model.getContent(),
				courseId);
	}
	
	public NoteModel EntityToModel(NoteEntity entity)
	{
		CourseEntity ce = coursesService.findById(entity.getCourseId());
		if (ce == null)
			return null;
		
		return new NoteModel(entity.getId(), entity.getTitle(), entity.getCategory(), entity.getContent(),
				ce.getCode());
	}
}
