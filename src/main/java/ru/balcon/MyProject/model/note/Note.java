package ru.balcon.MyProject.model.note;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Note {
    public Note() {
    }

    public Note(MetaNote metaNote) {
        noteName = metaNote.getName();
//        rootFolderId = metaNote.getRootFolderId();
//        noteId = metaNote.getNoteId();
    }

    public Note(String noteName,EditorJsData editorJsData) {
        this.noteName = noteName;
        this.editorJsData = editorJsData;
    }

    //    private int noteId;
    @JsonProperty("noteName")
    private String noteName;
    @JsonProperty("editorJsData")
    private EditorJsData editorJsData;

//    private int rootFolderId;

    @Override
    public String toString() {
        return "Note{" +
                "noteName='" + noteName + '\'' +
                ", editorJsData=" + editorJsData +
                '}';
    }

    public EditorJsData getEditorJsData() {
        return editorJsData;
    }

    public String getNoteName() {
        return noteName;
    }
}

