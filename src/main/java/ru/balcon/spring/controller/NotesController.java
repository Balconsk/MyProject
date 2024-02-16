package ru.balcon.spring.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.balcon.spring.dao.NoteDAO;
import ru.balcon.spring.models.Note;

import java.util.Map;

// FIXME Пока решил с помощью редеректа
@Controller
@RequestMapping("/notes")
public class NotesController {
    NoteDAO noteDAO;
    @Autowired
    public NotesController(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }
    @GetMapping("")
    public String indexRedirect(){
        return "redirect:notes/";
    }
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("notes", noteDAO.getAllNotes());
        model.addAttribute("note", new Note());
        return "Notes/index";
    }

    @GetMapping("/add")
    public String addGet(Model model){
        model.addAttribute("note", new Note());
        return "Notes/Note/add";
    }
    @PostMapping("/add")
    public String addPost(@ModelAttribute("note") @Valid Note note, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ("Notes/note/add");
        }
        noteDAO.addNote(note);
        return "redirect:/notes";
    }
    @GetMapping("/{id}/edit")
    public String updateGet(Model model, @PathVariable("id") int id){
        model.addAttribute("note", noteDAO.getNoteById(id));
        return "Notes/Note/edit";
    }
    @GetMapping("/{id}")
    public String showTask(Model model, @PathVariable("id") int id){
        model.addAttribute("note", noteDAO.getNoteById(id));
        return "Notes/Note/note";
    }
    @PatchMapping("/{id}")
    public String updatePatch(@ModelAttribute("task") @Valid Note note, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "Notes/Note/edit";
        }
        noteDAO.updateNote(note);
        return "redirect:/notes";
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id){
        noteDAO.removeNoteById(id);
        return "redirect:/notes";
    }

    @DeleteMapping("/set")
    public String removeSet(@RequestParam Map<String, String> arguments){
        int id;
        for (Map.Entry<String, String> entry: arguments.entrySet()) {
            if(entry.getKey().startsWith("noteCheckboxId")&& entry.getValue().equals("on")){
                id = Integer.parseInt(entry.getKey().replace("noteCheckboxId",""));
                remove(id);
            }
        }

        return "redirect:/notes";
    }

}
