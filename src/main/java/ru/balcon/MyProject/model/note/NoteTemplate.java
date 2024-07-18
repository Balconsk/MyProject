package ru.balcon.MyProject.model.note;

import jakarta.validation.constraints.Size;

public class NoteTemplate {
    @Size(min = 1, max=60)
    String noteName;
    int parentFolderId;
    int consumer_id;

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public int getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(int parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public int getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(int consumer_id) {
        this.consumer_id = consumer_id;
    }
}
