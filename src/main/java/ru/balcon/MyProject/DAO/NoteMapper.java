package ru.balcon.MyProject.DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import ru.balcon.MyProject.model.EditorJsData;
import ru.balcon.MyProject.model.Note;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteMapper implements RowMapper<Note> {
    @Override
    public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
        String noteName = rs.getString("note_name");
        String note_data = rs.getString("note_data");
//        String noteJSON = "{ \"noteName\" : \""+noteName+"\", " + note_data+" }";
        ObjectMapper objectMapper = new ObjectMapper();
        EditorJsData editorJsData;
        try {
            editorJsData = objectMapper.readValue(note_data, EditorJsData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Note note = new Note(noteName ,editorJsData);
        return note;
    }
}
