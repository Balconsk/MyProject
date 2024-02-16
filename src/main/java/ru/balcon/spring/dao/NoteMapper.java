package ru.balcon.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.balcon.spring.models.Note;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
        Note note = new Note();
        note.setId(rs.getInt("id"));
        note.setName(rs.getString("name"));
        note.setDescription(rs.getString("description"));
        return note;
    }
}
