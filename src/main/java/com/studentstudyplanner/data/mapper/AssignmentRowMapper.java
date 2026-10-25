
package com.studentstudyplanner.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.AssignmentEntity;

/**
 * Converts assignment records from the database into AssignmentEntity objects.
 *
 * This class implements Spring's RowMapper interface to read assignment
 * information from a database result and create an AssignmentEntity
 * containing the retrieved values.
 */
@Component
public class AssignmentRowMapper implements RowMapper<AssignmentEntity> {

    /**
     * Maps a single database row to an AssignmentEntity object.
     *
     * Retrieves the assignment ID, name, category, due date,
     * completion status, and associated course ID from the
     * database result.
     *
     * @param rs the ResultSet containing the assignment information
     * @param rowNumber the number of the current row being processed
     * @return an AssignmentEntity containing the retrieved assignment data
     * @throws SQLException if an error occurs while reading the database values
     */
    @Override
    public AssignmentEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
        LocalDate ldt = rs.getObject("DUEDATE", LocalDate.class);

        return new AssignmentEntity(rs.getLong("ID"), rs.getString("NAME"), rs.getString("CATEGORY"),
                ldt, rs.getBoolean("FINISHED"), rs.getLong("COURSEID"));
    }
}
