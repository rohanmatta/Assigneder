package com.example.assigneder;

import android.os.Build;

import java.time.LocalDate;

public class Assignment implements Comparable<Assignment> {
    private String title;
    private Course course;
    private LocalDate dueDate;
    private String description;
    private int hierarchyStatus;

    public Assignment(String title, Course course, LocalDate due_date, String description){
        this.title = title;
        this.course = course;
        this.dueDate = due_date;
        this.description = description;
        this.hierarchyStatus = course.getHierarchy();
    }

    public String getTitle() {
        return title;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Assignment a) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(dueDate.compareTo(a.getDueDate()) > 0){
                return -1;
            } else if (dueDate.compareTo(a.getDueDate()) < 0) {
                return 1;
            } else if (dueDate.compareTo(a.getDueDate()) == 0) {
                return 0;
            }
        }
        return 0;
    }
}
