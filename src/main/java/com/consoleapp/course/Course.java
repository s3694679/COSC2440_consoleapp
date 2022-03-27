package com.consoleapp.course;

import java.util.Objects;

public class Course implements Comparable<Course>{
    private String id;
    private String name;
    private String credits;

    public Course (String id, String name, String credits) {
        super();
        this.id = id;
        this.name= name;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course ID: '" + id +
                ", Name: '" + name +
                ", Credits: '" + credits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id) && name.equals(course.name) && credits.equals(course.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits);
    }

    @Override
    public int compareTo(Course o) {
        return id.compareTo(o.getId());
    }
}
