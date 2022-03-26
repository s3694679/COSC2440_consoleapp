package com.consoleapp;

import com.consoleapp.course.Course;
import com.consoleapp.enrolment.*;
import com.consoleapp.student.Student;
import com.consoleapp.misc.Parser;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.File;
import java.util.*;

import static com.consoleapp.misc.Commands.*;
import static com.consoleapp.misc.Utils.*;

public class Main {
    private static final String[] menuHeader = {
            "ENROLMENT MANAGEMENT SYSTEM",
            "=============================",
    };

    private static final String[] menuLoad = {
            "WELCOME! Do you want to load your own file?",
            "=========================================",
            "[1] Yes ",
            "[2] No, use DEFAULT",};

    private static final String[] menuMain = {
            "[1] Create an enrolment",
            "[2] View all enrolments",
            "[3] View a student's enrolments",
            "[4] View a course's students",
            "[5] View a semester's courses",
            "[6] Update enrolments",
            "[7] Delete an enrolment",
            "[8] Export selected list",
            "[9] Reset selected list"
    };

    private static final String[] menuExit = {
            "---------------------------------",
            "[0] Quit"};

    public static void main(String[] args) {
        List<String[]> data;
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<StudentEnrolment> tempEnrolList;
        EnrolmentList enrolmentList = EnrolmentList.getInstance();

        String fileName = loadMenu();

        try {
            data = Parser.parser(fileName);

            students = Parser.studentHandler(data);
            courses = Parser.courseHandler(data);
            tempEnrolList = Parser.enrolmentHandler(data, students, courses);
            for (StudentEnrolment enrolment : tempEnrolList) {
                enrolmentList.add(enrolment);
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        if (courses.isEmpty() || students.isEmpty()) {
            System.out.println("0 students or courses were found. Exiting");
            waitFor(2000);
            System.exit(1);
        }

        boolean quit = false;
        do {
            printMenu(menuHeader);
            printMenu(menuMain);
            printMenu(menuExit);
            System.out.print("Choose your option : ");
            String input = getInput();
            switch (Objects.requireNonNull(input)) {
                case "1" -> createEnrolment(students, courses, enrolmentList);
                case "2" -> viewAll(enrolmentList.getAll());
                case "3" -> viewOneStudent(students, enrolmentList);
                case "4" -> viewOneCourse(courses, enrolmentList);
                case "5" -> viewOneSemester(enrolmentList);
                case "6" -> updateEnrolment(students, courses, enrolmentList);
                case "7" -> deleteEnrolment(enrolmentList);
                case "8" -> exportEnrolments(enrolmentList);
                case "9" -> unSetList(enrolmentList);
                case "0" -> {
                    System.out.println("Exiting!");
                    waitFor(2000);
                    quit = true;
                }
                default -> {
                    System.out.println("Invalid input!");
                    waitFor(2000);
                }
            }
        } while (!quit);

    }
    public static String loadMenu() {
        String fileName = "src/main/resources/default.csv";
        boolean quit = false;
        do {
            printMenu(menuLoad);
            printMenu(menuExit);
            System.out.print("Choose your option : ");
            String input = getInput();
            switch (Objects.requireNonNull(input)) {
                case "1" -> {
                    System.out.println("Enter file's path");
                    fileName = getInput();
                    File temp = new File(fileName);
                    if (temp.isFile()) {
                        quit = true;
                    } else {
                        System.out.println("File Not Found. Returning to menu");
                        waitFor(2000);
                    }
                }
                case "2" -> quit = true;
                case "0" -> {
                    System.out.println("Exiting!");
                    quit = true;
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid input!");
                    waitFor(2000);
                }
            }
        } while (!quit);
        return fileName;
    }
}

