package com.example.vasic.kvalifikacijeexecom.model;

/**
 * Created by vasic on 12/1/2016.
 */

public class Task {
    private Integer id;

    private String title;

    private String description;

    private boolean finished;

    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;         // boolean
    }

    public Task(int id, String title, String description, boolean finished) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.finished = finished;
    }

    public Integer getId() {
        return id;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", finished=" + finished +
                '}';
    }
}
