package com.studentstudyplanner.data.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.AssignmentEntity;

@Component
public class AssignmentRowMapper implements RowMapper<AssignmentEntity> {
	@Override
	public AssignmentEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Date in = rs.getDate("DUEDATE");
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		
		return new AssignmentEntity(rs.getLong("ID"), rs.getString("NAME"), rs.getString("CATEGORY"),
				ldt, rs.getBoolean("FINISHED"), rs.getLong("COURSEID"));
	}
}