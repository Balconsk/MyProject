package ru.balcon.spring.models;

import ru.balcon.spring.parser.HTMLTagToCode;

public class HTMLElement {
    private HTMLTagToCode code;
    private String line;

    public HTMLElement() {
    }

    public HTMLElement(HTMLTagToCode code, String line) {
        this.code = code;
        this.line = line;
    }

    public HTMLTagToCode getCode() {
        return code;
    }

    public void setCode(HTMLTagToCode code) {
        this.code = code;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
