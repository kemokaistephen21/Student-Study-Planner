
package com.studentstudyplanner.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.CourseEntity;

/**
 * Converts course records from the database into CourseEntity objects.
 *
 * This class implements Spring's RowMapper interface to retrieve
 * course information from a database result and create a
 * CourseEntity containing the retrieved values.
 */
@Component
public class CourseRowMapper implements RowMapper<CourseEntity> {

    /**
     * Maps a single database row to a CourseEntity object.
     *
     * Retrieves the course ID, name, code, instructor,
     * and associated user ID from the database result.
     *
     * @param rs the ResultSet containing the course information
     * @param rowNumber the number of the current row being processed
     * @return a CourseEntity containing the retrieved course data
     * @throws SQLException if an error occurs while reading database values
     */
    @Override
    public CourseEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
        return new CourseEntity(rs.getLong("ID"), rs.getString("NAME"), rs.getString("CODE"),
                rs.getString("INSTRUCTOR"), rs.getLong("USERID"));
    }
}
