package com.example.assigneder;

public class Course implements Comparable<Course> {
    private String name;
    private int hierarchy;

    public Course(String name, int hierarchy){
        this.name = name;
        this.hierarchy = hierarchy;
    }

    public String getName(){
        return name;
    }

    public int getHierarchy(){
        return hierarchy;
    }

    @Override
    public int compareTo(Course o) {
        return 0;
    }
}
