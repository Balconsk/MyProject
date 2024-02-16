package ru.balcon.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.balcon.spring.models.Note;
import java.util.List;

@Component
public class NoteDAO {
    private final JdbcTemplate jdbcTemplate;
    public int getAvailableID(){
        Integer occupiedID;
        occupiedID = jdbcTemplate.queryForObject("select sum(lastid) from lastid;",Integer.class);

        if (occupiedID==null){
//            FIXME Add log
            return -1;
        }
        int availableID = occupiedID+1;
        jdbcTemplate.update("UPDATE lastid SET lastid=? WHERE id=1",availableID);
        return availableID;
    }

    @Autowired
    public NoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Note> getAllNotes(){
        return jdbcTemplate.query("SELECT * FROM note", new NoteMapper());
    }
    public Note getNoteById(int id){
        return  jdbcTemplate.query("SELECT * FROM note WHERE id=?", new NoteMapper(), id).get(0);
    }
    public void addNote(Note note){
        jdbcTemplate.update("INSERT INTO note VALUES (?, ?, ?);",
                getAvailableID(),
                note.getName(),
                note.getDescription());
    }
    public void removeNoteById(int id){
        jdbcTemplate.update("DELETE FROM note WHERE id=?", id);
    }

    public void updateNote(Note note){
        jdbcTemplate.update("UPDATE note SET name=?, description=?, WHERE id=?",
                note.getName(),
                note.getDescription(),
                note.getId());
    }
}
