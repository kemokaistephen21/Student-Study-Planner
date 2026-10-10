package com.studentstudyplanner.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.AssignmentEntity;

@Component
public class AssignmentRowMapper implements RowMapper<AssignmentEntity> {
	@Override
	public AssignmentEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
		LocalDate ldt = rs.getObject("DUEDATE", LocalDate.class);
		
		return new AssignmentEntity(rs.getLong("ID"), rs.getString("NAME"), rs.getString("CATEGORY"),
				ldt, rs.getBoolean("FINISHED"), rs.getLong("COURSEID"));
	}
}