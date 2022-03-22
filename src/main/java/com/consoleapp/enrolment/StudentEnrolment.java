package com.consoleapp.enrolment;

import com.consoleapp.course.Course;
import com.consoleapp.student.Student;

import java.util.ArrayList;

public class StudentEnrolment implements StudentEnrolmentManager{
    private Student student;
    private Course course;
    private String semester;
    private ArrayList<StudentEnrolment> enrolmentList = new ArrayList<>();

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
        return "src.java.com.consoleapp.enrolment.StudentEnrolment{" +
                "src.java.com.consoleapp.student=" + student +
                ", src.java.com.consoleapp.course=" + course +
                ", semester='" + semester + '\'' +
                '}';
    }

    @Override
    public void add(StudentEnrolment enrolment) {
        enrolmentList.add(enrolment);
    }

    @Override
    public void update(StudentEnrolment oldEnrol, StudentEnrolment newEnrol) {
        oldEnrol.setCourse(newEnrol.getCourse());
        oldEnrol.setStudent(newEnrol.getStudent());
        oldEnrol.setSemester(newEnrol.getSemester());
    }

    @Override
    public void delete(StudentEnrolment enrolment) {
        enrolmentList.remove(enrolment);
    }

    @Override
    public void getOne(StudentEnrolment target) {
//
    }

    @Override
    public ArrayList<StudentEnrolment> getAll() {
        return this.enrolmentList;
    }
}
