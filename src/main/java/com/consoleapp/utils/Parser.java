package com.consoleapp.utils;

import com.consoleapp.course.Course;
import com.consoleapp.enrolment.StudentEnrolment;
import com.consoleapp.student.Student;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parser {
    public static List<String[]> parser(String fileName) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();
            return r;
        }
    }

    public static ArrayList<Student> studentHandler(List<String[]> r) {
        ArrayList<Student> students = new ArrayList<>();
        for (String[] l : r){
            if(l.length >= 7){
                String studentId = l[0];
                String studentName = l[1];
                String studentBirthdate = l[2];
                Student current = new Student.StudentBuilder(studentId, studentName)
                        .birthdate(studentBirthdate)
                        .build();
                if(students.contains(current)) {
                    continue;
                } else {
                    students.add(current);
                };
            }
        }
        return students;
    }

    public static ArrayList<Course> courseHandler(List<String[]> r) {
        ArrayList<Course> courses = new ArrayList<>();
        for (String[] l : r){
            if(l.length >= 7){
                String courseId = l[3];
                String courseName = l[4];
                String courseCredits = l[5];
                Course current = new Course(courseId,courseName, courseCredits);
                if(courses.contains(current)) {
                    continue;
                } else {
                    courses.add(current);
                };
            }
        }
        return courses;
    }

    public static ArrayList<StudentEnrolment> enrolmentHandler(List<String[]> r, ArrayList<Student> students, ArrayList<Course> courses) {
        ArrayList<StudentEnrolment> enrolments = new ArrayList<>();
        for(String[] l : r){
            if(l.length >= 7){
                Student currentStudent = null;
                Course currentCourse = null;
                String studentId = l[0];
                for (Student student : students){
                    if (Objects.equals(student.getId(), studentId)) {

                        currentStudent = student;
                    }
                }
                String courseId = l[3];
                for (Course course : courses){
                    if (Objects.equals(course.getId(), courseId)) {
                        currentCourse = course;
                    }
                }
                String semester = l[6];
                if (currentStudent != null & currentCourse != null){
                    StudentEnrolment current = new StudentEnrolment(currentStudent, currentCourse, semester);
                    enrolments.add(current);
                }
            }
        }
        return enrolments;
    }
}
