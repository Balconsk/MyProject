package ru.balcon.MyProject.model.folder;

import jakarta.validation.constraints.Size;

public class FolderTemplate {
    @Size(min = 1,max = 60)
    String folderName;
    int rootFolderId;

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    int consumerId;

    public int getRootFolderId() {
        return rootFolderId;
    }

    public void setRootFolderId(int rootFolderId) {
        this.rootFolderId = rootFolderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
