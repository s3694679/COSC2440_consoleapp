package com.consoleapp;

import com.consoleapp.course.Course;
import com.consoleapp.enrolment.EnrolmentList;
import com.consoleapp.enrolment.StudentEnrolment;
import com.consoleapp.student.Student;
import com.consoleapp.utils.Parser;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.File;
import java.util.*;

public class Main {
    private static final String[]  menuHeader = {
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
            "[3] Update an enrolment",
            "[4] Delete an enrolment",
            "[5] View a student's enrolments",
            "[6] View a course's students",
            "[7] View a semester's courses",
    };

    private static final String[] menuExit = {
            "---------------------------------",
            "[0] Quit"};

    public static void main(String[] args) {
        List<String[]> data;
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<StudentEnrolment> tempEnroList;
        EnrolmentList enrolmentList = EnrolmentList.getInstance();

        String fileName = loadMenu();

        try {
            data = Parser.parser(fileName);
            students = Parser.studentHandler(data);
            courses = Parser.courseHandler(data);
            tempEnroList = Parser.enrolmentHandler(data, students, courses);
            for (StudentEnrolment enrolment : tempEnroList) {
                enrolmentList.add(enrolment);
            }
        } catch (IOException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        if(courses.isEmpty() || students.isEmpty()){
            System.out.println("0 students or courses were found. Exiting");
            waitFor(2000);
            System.exit(1);
        }

        boolean quit = false;
        do
        {
            printMenu(menuHeader);
            printMenu(menuMain);
            printMenu(menuExit);
            System.out.print("Choose your option : ");
            String input = getInput();
            switch (Objects.requireNonNull(input)) {
                case "1" -> {createEnrolment(students, courses, enrolmentList);}
                case "2" -> {viewAll(enrolmentList.getAll());}
                case "3" -> {}
                case "4" -> {deleteEnrolment(enrolmentList);}
                case "5" -> {}
                case "6" -> {}
                case "7" -> {}
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
        } while(!quit);

    }

    public static void printMenu(String[] menu){
        for (String option : menu){
            System.out.println(option);
        }
    }

    public static String loadMenu() {
        String fileName = "src/main/resources/default.csv";
        boolean quit = false;
        do
        {
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
        } while(!quit);
        return fileName;
    }

    public static void viewAll(ArrayList<StudentEnrolment> enrolmentList) {
        Collections.sort(enrolmentList);
        long i = 0;
        String separator = "-".repeat(86);
        String headerFmt = "%-2s | %-25s | %-40s | %-8s |\n";
        System.out.println(separator);
        System.out.format(headerFmt, "#", "Student Name(ID)", "Course Name(ID)", "Semester");
        System.out.println(separator);
        for (StudentEnrolment enrolment : enrolmentList) {
            Student student = enrolment.getStudent();
            Course course = enrolment.getCourse();
            String semester = enrolment.getSemester();
            String fmt = "%02d | %-25s | %-40s | %-8s |\n";
            System.out.format(fmt, i, student.getName() + "(" + student.getId() + ")",  course.getName() + "(" + course.getId() + ")", semester);
            i++;
        }
        System.out.println(separator);
        waitFor(2000);
    }

    public static void createEnrolment(ArrayList<Student> students, ArrayList<Course> courses, EnrolmentList enrolmentList) {
        System.out.println("Input student ID: ");
        String input = getInput();
        Student studentToEnrol = null;
        for (Student student : students) {
            if (student.getId().equals(input)) {
                studentToEnrol = student;
                break;
            }
        }
        if (studentToEnrol == null) {
            System.out.println("Invalid Student ID!");
            waitFor(2000);
            return;
        } else {
            System.out.println("Enrolling [" + studentToEnrol.getName() + "]...");
        }

        System.out.println("Input course ID: ");
        input = getInput();
        Course courseToEnrol = null;
        for (Course course : courses) {
            if (course.getId().equals(input)) {
                courseToEnrol = course;
                break;
            }
        }
        if (courseToEnrol == null) {
            System.out.println("Invalid Course ID!");
            waitFor(2000);
            return;
        } else {
            System.out.println("...into [" + courseToEnrol.getName() + "] for the semester...");
        }

        System.out.println("Input semester: ");
        input = getInput();

        StudentEnrolment enrolment = new StudentEnrolment(studentToEnrol, courseToEnrol, input);

        boolean enrolmentExists = false;
        for (StudentEnrolment enrolmentToCompare : enrolmentList.getAll()) {
            if(enrolmentToCompare.equals(enrolment)){
                enrolmentExists = true;
                break;
            }
        }
        if (!enrolmentExists) {
            enrolmentList.add(enrolment);
            System.out.printf("Enrolled %s into %s for the %s semester\n", enrolment.getStudent().getName(), enrolment.getCourse().getName(), enrolment.getSemester());
            waitFor(2000);
        } else {
            System.out.println("ERROR: Enrolment already exists!");
            waitFor(2000);
        }
    }

    public static void updateEnrolment(ArrayList<Student> students, ArrayList<Course> courses, EnrolmentList enrolmentList) {

    }

    public static void deleteEnrolment(EnrolmentList enrolmentList) {
        ArrayList<StudentEnrolment> enrolments = enrolmentList.getAll();
        Collections.sort(enrolments);

        viewAll(enrolments);

        int intPut;
        String input = getInput();
        if(input.matches("\\d+")) {
            intPut = Integer.parseInt(input);
        } else {
            System.out.println("Invalid input, deletion canceled.");
            waitFor(2000);
            return;
        }

        StudentEnrolment enrolmentToDelete = enrolments.get(intPut);
        boolean quit = false;
        do {
            System.out.println(enrolmentToDelete);
            System.out.println("Are you sure about deleting this enrolment? Y/N");
            input = getInput();
            switch (input){
                case "Y", "y" -> {;
                    enrolmentList.delete(enrolmentToDelete);
                    System.out.println("Deleted!");
                    waitFor(2000);
                    quit = true;
                }
                case "N", "n" -> {
                    System.out.println("Deletion canceled. Returning to menu...");
                    waitFor(2000);
                    quit = true;
                }
                default -> System.out.println("Invalid input!");
            }
        } while(!quit);

    }

    public static void viewOneStudent(ArrayList<Student> students, EnrolmentList enrolmentList) {}

    public static void viewOneCourse(ArrayList<Course> courses, EnrolmentList enrolmentList) {}

    public static void viewOneSemester(EnrolmentList enrolmentList) {}

    public static String getInput(){
        Scanner scan = new Scanner(System.in);
        if (scan.hasNext()) {
            return scan.next();
        }  else return null;
    }

    public static void waitFor(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
