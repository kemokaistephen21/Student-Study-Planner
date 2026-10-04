package com.studentstudyplanner.data.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.TaskEntity;

@Component
public class TaskRowMapper implements RowMapper<TaskEntity> {
	@Override
	public TaskEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Date in = rs.getDate("DUEDATE");
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		
		return new TaskEntity(rs.getLong("ID"), rs.getString("NAME"), ldt, rs.getString("CATEGORY"),
				rs.getString("DESCRIPTION"), rs.getLong("COURSEID"));
	}
}