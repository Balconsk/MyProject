package ru.balcon.MyProject.model;

public class MetaNote {
    int noteId;
    int rootFolderId;
    String name;

    public MetaNote(int noteId, String name) {
        this.noteId = noteId;
        this.name = name;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(int rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
