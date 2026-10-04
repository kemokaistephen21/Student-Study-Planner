package com.studentstudyplanner.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.NoteEntity;

@Component
public class NoteRowMapper implements RowMapper<NoteEntity> {
	@Override
	public NoteEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
		return new NoteEntity(rs.getLong("ID"), rs.getString("TITLE"), rs.getString("CATEGORY"),
				rs.getString("CONTENT"),rs.getLong("COURSEID"));
	}
}