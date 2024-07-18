package ru.balcon.MyProject.model.folder;

public class MetaFolder {
    int id;
    String name;
    int countNotesInFolder;

    public MetaFolder(int id, String name, int countNotesInFolder) {
        this.id = id;
        this.name = name;
        this.countNotesInFolder = countNotesInFolder;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountNotesInFolder() {
        return countNotesInFolder;
    }
}
