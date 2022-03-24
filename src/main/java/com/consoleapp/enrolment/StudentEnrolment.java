package com.consoleapp.enrolment;

import com.consoleapp.course.Course;
import com.consoleapp.student.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class StudentEnrolment implements Comparable<StudentEnrolment> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrolment that = (StudentEnrolment) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(course, that.course) &&
                Objects.equals(semester, that.semester);
    }

    @Override
    public int compareTo(StudentEnrolment o) {
        return this.getSemester().compareTo((o.getSemester()));
    }
}
