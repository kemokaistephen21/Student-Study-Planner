package com.studentstudyplanner.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.CourseEntity;

@Component
public class CourseRowMapper implements RowMapper<CourseEntity> {
	@Override
	public CourseEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
		return new CourseEntity(rs.getLong("ID"), rs.getString("NAME"), rs.getString("CODE"),
				rs.getString("INSTRUCTOR"), rs.getLong("USERID"));
	}
}