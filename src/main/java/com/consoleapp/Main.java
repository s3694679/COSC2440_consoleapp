package com.consoleapp;

import com.consoleapp.course.Course;
import com.consoleapp.student.Student;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student.StudentBuilder("S101312", "Alex Mike").birthdate("10/13/1998")
                .build();
        Student s2 = new Student.StudentBuilder("S102732", "Mark Duong").birthdate("8/28/2001").build();
        Course c1 = new Course("COSC4030", "Theory of Computation", "5");
        Course c2 = new Course("BUS2232", "Business Law", "3");

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(c1);
        courseList.add(c2);

        for (Student student : studentList) {
            System.out.println(student.toString());
        }

        for (Course course : courseList) {
            System.out.println(course.toString());
        }

        s1.setName("something");
        System.out.println(s1.toString());
    }
}
