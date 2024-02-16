package ru.balcon.spring.parser;

import ru.balcon.spring.models.HTMLElement;

import java.util.ArrayList;

public class TaskParserToHtml extends Parser {

// FIXME
//    Нужно возращать не map а ArrayList с каким нибудь классом который будет включать ключ значение
//    По сути матрица но вместо второго уровня отдельный класс (Может есть класс который это деалет)
    public static ArrayList<HTMLElement> parse(String text){
        ArrayList<HTMLElement> list = new ArrayList<>();
        for (String line:text.split("\n")){
            list.add( identifyLine(line) );
        }
        return list;
    }

    public static HTMLElement identifyLine(String line){
        int i=0;
        HTMLTagToCode code = HTMLTagToCode.TEXT;
        //        Headers
        if (line.startsWith("######")) {
            code = HTMLTagToCode.HEADER6;
            i=6;
        }else if (line.startsWith("#####")) {
            code = HTMLTagToCode.HEADER5;
            i=5;
        }else if (line.startsWith("####")) {
            code = HTMLTagToCode.HEADER4;
            i=4;
        }else if (line.startsWith("###")) {
            code = HTMLTagToCode.HEADER3;
            i=3;
        }else if (line.startsWith("##")) {
            code = HTMLTagToCode.HEADER2;
            i=2;
        }else if (line.startsWith("#")) {
            code = HTMLTagToCode.HEADER1;
            i=1;
        }
//        else if (line.startsWith("-")){
//            code = HTMLTagToCode.UNORDERED_LIST_ITEM;
//            i=1;
//        }
        else if (line.startsWith("[ ]")){
            code = HTMLTagToCode.CHECKBOX_TRUE;
            i=3;
        }else if (line.startsWith("[X]")|| line.startsWith("[x]")){
            code = HTMLTagToCode.CHECKBOX_FALSE;
            i=3;
        }else if (line.startsWith("***")){
            code = HTMLTagToCode.HORIZONTAL_RULE;
            i=3;
        }
        return new HTMLElement(code,line.substring(i));


    }


}
