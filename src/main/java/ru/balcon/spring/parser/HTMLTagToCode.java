package ru.balcon.spring.parser;

public enum HTMLTagToCode {
    TEXT(0),

    HEADER1(1),
    HEADER2(2),
    HEADER3(3),
    HEADER4(4),
    HEADER5(5),
    HEADER6(6),

    BOLD(2),
    ORDER_LIST_ITEM(10),
    UNORDERED_LIST_ITEM(11),

    CHECKBOX_TRUE(20),
    CHECKBOX_FALSE(21),

    URL_BASIC(30),
    MARK(40),

    STRIKE(50),
    HORIZONTAL_RULE(60);

    private int code;

    HTMLTagToCode(int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
}

//7)(Oprtional) элементы 2 уровня и т д
//7) (Oprtional) Callout
//7) (Oprtional) Подержку таблиц