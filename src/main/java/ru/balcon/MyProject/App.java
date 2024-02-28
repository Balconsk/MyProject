package ru.balcon.MyProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.balcon.MyProject.model.Note;

public class App {
    public static void main(String[] args) throws JsonProcessingException {
        String json = "{ \"noteName\" : \"Note\", " +
                "\"editorJsData\" : {\"time\":1708268570412,\"blocks\":[],\"version\":\"2.29.0\"} }";


        ObjectMapper objectMapper = new ObjectMapper();

        Note note = objectMapper.readValue(json, Note.class);
        System.out.println(note);
    }
}
//{"NoteName":"Loading...","editorJsData":{"time":1708268570412,"blocks":[],"version":"2.29.0"}}
