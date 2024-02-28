package ru.balcon.MyProject.model;

import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    @Size(min = 1, max=60)
    String folderName;
    int id;
    int rootFolderId;
    int consumerId;

    public void setItemsFolder(List<MetaFolder> itemsFolder) {
        this.itemsFolder = itemsFolder;
    }

    public void setItemsNote(List<MetaNote> itemsNote) {
        this.itemsNote = itemsNote;
    }

    List<MetaFolder> itemsFolder;
    List<MetaNote> itemsNote;

    public Folder() {
        this.itemsFolder = new ArrayList<MetaFolder>();
        this.itemsNote = new ArrayList<MetaNote>();
    }

    public Folder(List<MetaFolder> itemsFolder, List<MetaNote> itemsNote) {
        this.itemsFolder = itemsFolder;
        this.itemsNote = itemsNote;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(int rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

    public void addFolder(int id, String name, int countNotesInFolder){
        itemsFolder.add(new MetaFolder(id,name,countNotesInFolder));
    }
    public void addNote(int id, String name){
        itemsNote.add(new MetaNote(id,name));
    }

    public List<MetaFolder> getItemsFolder() {
        return itemsFolder;
    }

    public List<MetaNote> getItemsNote() {
        return itemsNote;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}

