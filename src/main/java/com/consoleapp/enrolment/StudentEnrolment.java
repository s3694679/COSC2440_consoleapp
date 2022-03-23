package com.consoleapp.enrolment;

import com.consoleapp.course.Course;
import com.consoleapp.student.Student;

import java.util.ArrayList;

public class StudentEnrolment {
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Enrolment:" +
                " \'" + student.getName() +
                "\'" + " is in " + "\'" + course.getName() +
                "\'" + " for the "+ "\'" + semester + "\'" + " semester.";
    }
}
