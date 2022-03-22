package com.consoleapp;

import com.consoleapp.course.Course;
import com.consoleapp.enrolment.EnrolmentList;
import com.consoleapp.enrolment.StudentEnrolment;
import com.consoleapp.student.Student;
import com.consoleapp.utils.Parser;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Student s1 = new Student.StudentBuilder("S101312", "Alex Mike")
//                .birthdate("10/13/1998")
//                .build();
//        Student s2 = new Student.StudentBuilder("S102732", "Mark Duong")
//                .birthdate("8/28/2001")
//                .build();
//        Course c1 = new Course("COSC4030", "Theory of Computation", "5");
//        Course c2 = new Course("BUS2232", "Business Law", "3");
//
//        ArrayList<Student> studentList = new ArrayList<>();
//        studentList.add(s1);
//        studentList.add(s2);
//
//        ArrayList<Course> courseList = new ArrayList<>();
//        courseList.add(c1);
//        courseList.add(c2);
//
//        for (Student student : studentList) {
//            System.out.println(student.toString());
//        }
//
//        for (Course course : courseList) {
//            System.out.println(course.toString());
//        }
        EnrolmentList enrolmentList = EnrolmentList.getInstance();
        String fileName = "C:\\Users\\asus\\Desktop\\COSC2440\\COSC2440_consoleapp\\src\\main\\resources\\default.csv";
        try {
            List<String[]> data = Parser.parser(fileName);
            ArrayList<Student> students = Parser.studentHandler(data);
            ArrayList<Course> courses = Parser.courseHandler(data);
            ArrayList<StudentEnrolment> enrolments = Parser.enrolmentHandler(data, students, courses);
            for (StudentEnrolment enrolment : enrolments) {
                enrolmentList.add(enrolment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
        enrolmentList.getAll().forEach(x -> System.out.println(x.toString()));
    }

}
