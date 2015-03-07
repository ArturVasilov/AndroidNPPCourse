package com.example.root.nppsimplesqliteexample;

import com.example.root.nppsimplesqliteexample.dao.Subject;

public class Timetable {
    private long id;
    private String lecturer;
    private long time;
    private Subject subject;

    public Timetable(long id, Subject subject, long time, String lecturer){
        this.id = id;
        this.time = time;
        this.lecturer = lecturer;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
