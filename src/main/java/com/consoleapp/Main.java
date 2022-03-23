package com.consoleapp;

import com.consoleapp.course.Course;
import com.consoleapp.enrolment.EnrolmentList;
import com.consoleapp.enrolment.StudentEnrolment;
import com.consoleapp.student.Student;
import com.consoleapp.utils.Parser;
import com.opencsv.exceptions.CsvException;

import java.io.Console;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String[]  menuHeader = {
            "ENROLMENT MANAGEMENT SYSTEM",
            "=============================",
    };

    private static String[] menu1 = {
            "WELCOME! Do you want to load your own file?",
            "=========================================",
            "[1] Yes ",
            "[2] No, use DEFAULT",};

    private static String[] menu2 = {
            "[1] Create an enrolment",
            "[2] View all enrolments",
            "[3] Update an enrolment",
            "[4] Delete an enrolment",
            "[5] View a student's enrolments",
            "[6] View a course's students",
            "[7] View a semester's courses",
    };

    private static String[] menuExit = {
            "---------------------------------",
            "[0] Quit"};

    public static void main(String[] args) {
        List<String[]> data;
        ArrayList<Student> students;
        ArrayList<Course> courses;
        ArrayList<StudentEnrolment> enrolments;
        EnrolmentList enrolmentList = EnrolmentList.getInstance();

        String fileName = loadMenu();

        try {
            data = Parser.parser(fileName);
            students = Parser.studentHandler(data);
            courses = Parser.courseHandler(data);
            enrolments = Parser.enrolmentHandler(data, students, courses);
            for (StudentEnrolment enrolment : enrolments) {
                enrolmentList.add(enrolment);
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        String input = "";
        boolean quit = false;
        do
        {
            printMenu(menuHeader);
            printMenu(menu2);
            printMenu(menuExit);
            System.out.print("Choose your option : ");
            if (scan.hasNext()) {
                input = scan.next();
            }
            switch (input) {
                case "1" -> System.out.println("Please enter the file's path:");
                case "2" -> {viewAll(enrolmentList.getAll());}
                case "3" -> {}
                case "4" -> {}
                case "5" -> {}
                case "6" -> {}
                case "7" -> {}
                case "0" -> {
                    System.out.println("Exiting!");
                    quit = true;
                    System.exit(0);
                }
                default -> System.out.println("Invalid input!");
            }
        } while(!quit);

    }

    public static void printMenu(String[] menu){
        for (String option : menu){
            System.out.println(option);
        }
    }

    public static String loadMenu() {
        String fileName = "C:\\Users\\asus\\Desktop\\COSC2440\\COSC2440_consoleapp\\src\\main\\resources\\default.csv";
        Scanner scan = new Scanner(System.in);
        String input = "";
        boolean quit = false;
        do
        {
            printMenu(menu1);
            printMenu(menuExit);
            System.out.print("Choose your option : ");
            if (scan.hasNext()) {
                input = scan.next();
            }
            switch (input) {
                case "1" -> {
                    System.out.println("Enter file's path");
                    if (scan.hasNext()) {
                        fileName = scan.next();
                    }
                    File temp = new File(fileName);
                    if (temp.isFile()) {
                        quit = true;
                    }
                    System.out.println("File Not Found. Returning to menu");
                }
                case "2" -> quit = true;
                case "0" -> {
                    System.out.println("Exiting!");
                    quit = true;
                    System.exit(0);
                }
                default -> System.out.println("Invalid input!");
            }
        } while(!quit);
        System.out.println("done with load menu");
        return fileName;
    }

    public static void viewAll(ArrayList<StudentEnrolment> enrolmentList) {
        for (StudentEnrolment enrolment : enrolmentList) {
            Student student = enrolment.getStudent();
            Course course = enrolment.getCourse();
            String semester = enrolment.getSemester();
//            String fmt = "%-12s%-12s%-12s\n";
//            System.out.format(fmt, student.getName() + course.getName() + semester);
////            System.out.println(enrolment);
        }
    }

}
