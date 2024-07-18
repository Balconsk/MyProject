package ru.balcon.MyProject.model.note;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class EditorJsData {
    @JsonProperty("blocks")
    private List<Block> blocks = new ArrayList<>();
    @JsonProperty("time")
    private long time;
    @JsonProperty("version")
    private String version;
}
