package ru.balcon.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.balcon.spring.parser.TaskParserToHtml;

import java.util.ArrayList;

public class Note {
    @NotEmpty(message = "Name should not be empty")
    @Size (min = 2,max=50, message = "Name length should be between 2 and 50")
    String name;
    @Size(max=1000, message = "Description length should be less then 500")
    String description;
    private int id;


    public Note(String name) {
        this();
        this.name = name;
    }

    public Note(String name, String description) {
        this(name);
        this.description = description;
    }

    public Note() {
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public ArrayList<HTMLElement> getLinesDescription() {
        return TaskParserToHtml.parse(this.getDescription());
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
