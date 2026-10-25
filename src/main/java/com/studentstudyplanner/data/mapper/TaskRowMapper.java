
package com.studentstudyplanner.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.TaskEntity;

/**
 * Converts study task records from the database into TaskEntity objects.
 *
 * This class implements Spring's RowMapper interface to retrieve
 * study task information from a database result and create a
 * TaskEntity containing the retrieved values.
 */
@Component
public class TaskRowMapper implements RowMapper<TaskEntity> {

    /**
     * Maps a single database row to a TaskEntity object.
     *
     * Retrieves the task ID, name, due date, category,
     * description, and associated course ID from the database result.
     *
     * @param rs the ResultSet containing the study task information
     * @param rowNumber the number of the current row being processed
     * @return a TaskEntity containing the retrieved study task data
     * @throws SQLException if an error occurs while reading database values
     */
    @Override
    public TaskEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
        LocalDate ldt = rs.getObject("DUEDATE", LocalDate.class);

        return new TaskEntity(rs.getLong("ID"), rs.getString("NAME"), ldt, rs.getString("CATEGORY"),
                rs.getString("DESCRIPTION"), rs.getLong("COURSEID"));
    }
}
