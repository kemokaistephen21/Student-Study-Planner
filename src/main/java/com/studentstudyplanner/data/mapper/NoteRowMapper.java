
package com.studentstudyplanner.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.studentstudyplanner.data.entity.NoteEntity;

/**
 * Converts note records from the database into NoteEntity objects.
 *
 * This class implements Spring's RowMapper interface to retrieve
 * note information from a database result and create a
 * NoteEntity containing the retrieved values.
 */
@Component
public class NoteRowMapper implements RowMapper<NoteEntity> {

    /**
     * Maps a single database row to a NoteEntity object.
     *
     * Retrieves the note ID, title, category, content,
     * and associated course ID from the database result.
     *
     * @param rs the ResultSet containing the note information
     * @param rowNumber the number of the current row being processed
     * @return a NoteEntity containing the retrieved note data
     * @throws SQLException if an error occurs while reading database values
     */
    @Override
    public NoteEntity mapRow(ResultSet rs, int rowNumber) throws SQLException {
        return new NoteEntity(rs.getLong("ID"), rs.getString("TITLE"), rs.getString("CATEGORY"),
                rs.getString("CONTENT"),rs.getLong("COURSEID"));
    }
}
