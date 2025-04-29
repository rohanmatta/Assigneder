package com.example.assigneder;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.Month;

public class Assignment implements Comparable<Assignment> {

    public static class DateHeader{
        LocalDate date;
        String headerText;
        @RequiresApi(api = Build.VERSION_CODES.O)
        public DateHeader(LocalDate date){
            this.date = date;
            this.headerText = "Due: " + date.toString();
        }

        public void setCustomHeader(String header){
            this.headerText = header;
        }

        public String getHeaderText(){
            return headerText;
        }
    }
    private String title;
    private String course;
    private LocalDate dueDate;
    private String description;
    private boolean isUrgent;
    private boolean isCompleted;

    public Assignment(String title, String course, LocalDate due_date, String description){
        this.title = title;
        this.course = course;
        this.dueDate = due_date;
        this.description = description;
        this.isUrgent = false;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title;
    }

    public String getCourse() {
        return course;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setUrgent(boolean urgent) {
        isUrgent = urgent;
    }

    public boolean isUrgent(){
        return isUrgent;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(Assignment a) {
        if(dueDate.compareTo(a.getDueDate()) > 0){
            return -1;
        } else if (dueDate.compareTo(a.getDueDate()) < 0) {
            return 1;
        } else if (dueDate.compareTo(a.getDueDate()) == 0) {
            return 0;
        }
        return 0;
    }
}
