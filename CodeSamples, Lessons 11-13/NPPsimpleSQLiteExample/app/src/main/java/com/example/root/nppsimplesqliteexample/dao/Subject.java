package com.example.root.nppsimplesqliteexample.dao;

public enum Subject {
    MATH_LOGICS("Матлогика"),
    DATABASES("Базы данных"),
    OOP("Программирование");

    private String value;

    Subject(String value){
        this.value = value;
    }

    public static Subject fromString(String enumm){
        for (Subject subject : Subject.values()){
            if (subject.value.equals(enumm)) return subject;
        }
        return null;
    }

    public String getValue(){
        return value;
    }
}
