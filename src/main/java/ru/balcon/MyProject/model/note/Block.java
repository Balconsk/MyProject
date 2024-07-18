package ru.balcon.MyProject.model.note;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

class Block {
    @JsonProperty("data")
    private Map<String, Object> data;
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;

}
