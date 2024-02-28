package ru.balcon.MyProject.DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.balcon.MyProject.model.MetaNote;
import ru.balcon.MyProject.model.Note;
import ru.balcon.MyProject.model.NoteTemplate;

@Component
public class NoteDAO {
    ObjectMapper objectMapper;
    JdbcTemplate jdbcTemplate;
    @Autowired
    public NoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public Note getNoteById(int id){
        return jdbcTemplate.query("SELECT note_name, note_data FROM note WHERE note_id=?",
                new NoteMapper(),
                id)
                .get(0);
    }
    public void addNote(NoteTemplate noteTemplate){
        jdbcTemplate.update("INSERT INTO note(consumer_id, parent_folder_id, note_name, note_data) " +
                "VALUES (?,?,?,'{}' )",
                noteTemplate.getConsumer_id(),
                noteTemplate.getParentFolderId(),
                noteTemplate.getNoteName());
    }

    public void updateNote(Note note, int id){
        String noteData;
        try {
             noteData = objectMapper.writeValueAsString(note.getEditorJsData());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        jdbcTemplate.update("UPDATE note SET note_name=?, note_data=?  WHERE note_id=?",
                note.getNoteName(),
                noteData,
                id);
    }
    public void deleteNote(MetaNote metaNote){
        jdbcTemplate.update("DELETE FROM note WHERE note_id=?", metaNote.getNoteId());
    }

    public MetaNote getMetaNoteById(int id){
        return jdbcTemplate.query("SELECT note_id, note_name, parent_folder_id FROM note WHERE note_id=?",
                        new MetaNoteMapper(), id) .get(0);
    }

}
