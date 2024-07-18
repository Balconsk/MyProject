package ru.balcon.MyProject.controller.NoteApp;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.balcon.MyProject.DAO.NoteDAO;
import ru.balcon.MyProject.model.note.Note;

@RestController
@RequestMapping("/note-app/api/v1/")
public class NoteAppRESTController {
    NoteDAO noteDAO;
    @Autowired
    public NoteAppRESTController(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    Note note = new Note();

    @GetMapping("/note/{id}")
    @ResponseBody
    public Note getMethod(@PathVariable("id") int id, HttpServletResponse response){
        return noteDAO.getNoteById(id);
    }


    @PostMapping("/note/{id}")
    @ResponseBody
    public String postMethod(@PathVariable("id") int id, @RequestBody Note note, HttpServletResponse response){
        noteDAO.updateNote(note, id);
        return "{\"Save\":\"OK\"}";
    }
}
