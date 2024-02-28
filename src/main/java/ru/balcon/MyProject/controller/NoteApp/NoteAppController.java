package ru.balcon.MyProject.controller.NoteApp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.balcon.MyProject.DAO.FolderDAO;
import ru.balcon.MyProject.DAO.NoteDAO;
import ru.balcon.MyProject.model.*;

@Controller
@RequestMapping("note-app")
public class NoteAppController {

    Consumer consumer = new Consumer(5,"user1");
    FolderDAO folderDAO;
    NoteDAO noteDao;

    @Autowired
    public NoteAppController(FolderDAO folderDAO, NoteDAO noteDao) {
        this.folderDAO = folderDAO;
        this.noteDao = noteDao;
    }

    @GetMapping("")
    public String redirect(){
        return "redirect:/note-app/";
    }

    @GetMapping("/")
    public String index(){
        int rootFolderId = folderDAO.getRootFolderIdByConsumer(consumer);
        return "redirect:/note-app/folder/"+rootFolderId;
    }

    @GetMapping("/folder/{id}")
    public String folderGet(@PathVariable("id") int id, Model model){
        Folder folder = folderDAO.getFolder(id);
        model.addAttribute("folder", folder);
        return "NotesApp/folder";
    }

    @DeleteMapping("/folder/{id}")
    public String folderDelete(@PathVariable("id") int id){
        folderDAO.deleteFolderById(id);
        return "redirect:/note-app/";
    }

    @GetMapping("/folder/edit-name/{id}")
    public String folderRename(@PathVariable("id") int id, Model model){
        model.addAttribute("folder", folderDAO.getFolder(id));
        return "NotesApp/editNameFolder";
    }
    @PatchMapping("/folder/edit-name/{id}")
    public String folderRenamePatch(@PathVariable("id") int id,
                                    @ModelAttribute("folder") @Valid Folder folderFromForm,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "NotesApp/editNameFolder";
        }
        Folder folder = folderDAO.getFolder(folderFromForm.getId());
        folder.setFolderName(folderFromForm.getFolderName());
        folderDAO.updateFolder(folder);
        return "redirect:/note-app/folder/"+folder.getRootFolderId();
    }

    @GetMapping("/folder/{id}/create-folder/")
    public String createFolder(@PathVariable("id") int id, Model model){
        FolderTemplate folderTemplate = new FolderTemplate();
        folderTemplate.setRootFolderId(id);
        model.addAttribute("folderTemplate", folderTemplate);
        return "NotesApp/createFolder";
    }

    @PostMapping("/folder/{id}/create-folder/")
    public String createFolderPost(@PathVariable("id") int id,
                                   @ModelAttribute("folderTemplate") @Valid FolderTemplate folderTemplate,
                                   BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "NotesApp/createFolder";
        }
        folderTemplate.setConsumerId(consumer.getId());
        folderTemplate.setRootFolderId(id);
        folderDAO.addFolder(folderTemplate);
        return "redirect:/note-app/folder/"+folderTemplate.getRootFolderId();
    }
    @GetMapping("/note/{id}")
    public String note(@PathVariable("id") int id, Model model){
        model.addAttribute("metaNote", noteDao.getMetaNoteById(id));
//        model.addAttribute("noteId", id);
        return "NotesApp/editor";
    }
    @DeleteMapping("/note/{id}")
    public String noteDelete(@PathVariable("id") int id){
        MetaNote metaNote = noteDao.getMetaNoteById(id);
        noteDao.deleteNote(metaNote);
        return "redirect:/note-app/";
    }
    @GetMapping("/note/edit-name/{id}")
    public String noteRename(@PathVariable("id") int id){
        return "NotesApp/editNameNote";
    }
    @GetMapping("/folder/{id}/create-note/")
    public String createNoteGet(@PathVariable("id") int id, Model model){
        NoteTemplate noteTemplate = new NoteTemplate();
        noteTemplate.setParentFolderId(id);
        model.addAttribute("noteTemplate", noteTemplate);
        return "NotesApp/createNote";
    }
    @PostMapping("/folder/{id}/create-note/")
    public String createNotePost(@PathVariable("id") int id,
                                @ModelAttribute("noteTemplate") @Valid NoteTemplate noteTemplate,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "NotesApp/createNote";
        }
        noteTemplate.setConsumer_id(consumer.getId());
        noteTemplate.setParentFolderId(id);
        noteDao.addNote(noteTemplate);
        return "redirect:/note-app/folder/"+noteTemplate.getParentFolderId();
    }
}
