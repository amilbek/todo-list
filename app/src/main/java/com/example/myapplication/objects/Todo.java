package com.example.myapplication.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Todo implements Serializable {

    private String title;
    private String description;
    private String date;
    private String time;
    private boolean isDone = false;

    public Todo(String title, String description, String date, String time) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return isDone == todo.isDone
                && Objects.equals(title, todo.title)
                && Objects.equals(description, todo.description)
                && Objects.equals(date, todo.date)
                && Objects.equals(time, todo.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date, isDone);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }
}
