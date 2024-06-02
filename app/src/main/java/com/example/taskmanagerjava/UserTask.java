package com.example.taskmanagerjava;
import java.io.Serializable;
import java.util.Date;

public class UserTask implements Serializable {

    private String name; //our title
    private String description;
    private Date deadline;
    private boolean isDone;
    private boolean isDaily;

    public UserTask(String name, String description, Date deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
    }

    public UserTask(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean IsDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        this.isDaily = daily;
    }

    @Override
    public String toString() {
        String information = "";
        information += ">title: " + name;
        information += " >description: " + description;
        return information;
    }
}
