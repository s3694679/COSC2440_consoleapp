package com.consoleapp;

import com.consoleapp.course.Course;
import com.consoleapp.enrolment.EnrolmentList;
import com.consoleapp.enrolment.StudentEnrolment;
import com.consoleapp.student.Student;
import com.consoleapp.utils.Parser;
import com.opencsv.exceptions.CsvException;
import org.w3c.dom.ls.LSOutput;

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
            "[3] Update enrolments",
            "[4] Delete an enrolment",
            "[5] View a student's enrolments",
            "[6] View a course's students",
            "[7] View a semester's courses",
            "[8] Print selected list",
            "[9] Reset selected list"
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
                case "3" -> {updateEnrolment(students, courses, enrolmentList);}
                case "4" -> {deleteEnrolment(enrolmentList);}
                case "5" -> {viewOneStudent(students, enrolmentList);}
                case "6" -> {viewOneCourse(courses, enrolmentList);}
                case "7" -> {viewOneSemester(enrolmentList);}
                case "8" -> {}
                case "9" -> {unSetList(enrolmentList);}
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

        addEnrolmentToList(enrolment, enrolmentList);
    }

    public static void updateEnrolment(ArrayList<Student> students, ArrayList<Course> courses, EnrolmentList enrolmentList) {
        ArrayList<StudentEnrolment> enrolments = enrolmentList.getCurrentList().isEmpty() ? enrolmentList.getAll() : enrolmentList.getCurrentList();
        Collections.sort(enrolments);
        String listType = enrolmentList.getCurrentListType();
        StudentEnrolment firstElement = enrolments.get(0);
        Student student = firstElement.getStudent();
        Course course = firstElement.getCourse();
        String studentID = student.getId();
        String courseID = course.getId();
        String semester = firstElement.getSemester();
        boolean newStudentInput = false;
        boolean newCourseInput = false;

        viewAll(enrolments);

        switch (listType) {
            case "student" -> {
                System.out.print("Enter courseID to enrol: ");
                courseID = getInput();
                newCourseInput = true;
            }
            case "course" -> {
                System.out.print("Enter studentID to enrol: ");
                studentID = getInput();
                newStudentInput = true;
            }
            case "semester" -> {
                System.out.print("Enter studentID to enrol: ");
                studentID = getInput();
                System.out.print("Enter courseID to enrol: ");
                courseID = getInput();
                newStudentInput = true;
                newCourseInput = true;
            }
            default -> {
                System.out.println("You did not choose a list to update. Select [1] from the menu if you want to create a new enrolment.");
                waitFor(2000);
                return;
            }
        }
        if(newStudentInput){
            Student studentToEnrol = null;
            for (Student s : students) {
                if (s.getId().equals(studentID)) {
                    studentToEnrol = s;
                    break;
                }
            }
            if (studentToEnrol == null) {
                System.out.println("Invalid Input!");
                waitFor(2000);
                return;
            } else {
                student = studentToEnrol;
            }
        }
        if(newCourseInput) {
            Course courseToEnrol = null;
            for (Course c : courses) {
                if (c.getId().equals(courseID)) {
                    courseToEnrol = c;
                    break;
                }
            }
            if (courseToEnrol == null) {
                System.out.println("Invalid Input!");
                waitFor(2000);
                return;
            } else {
                course = courseToEnrol;
            }
        }

        StudentEnrolment enrolment = new StudentEnrolment(student, course, semester);
        if(addEnrolmentToList(enrolment, enrolmentList)){
            enrolments.add(enrolment);
            enrolmentList.setCurrentList(enrolments);
        }
    }

    public static void deleteEnrolment(EnrolmentList enrolmentList) {
        ArrayList<StudentEnrolment> enrolments = enrolmentList.getCurrentList().isEmpty() ? enrolmentList.getAll() : enrolmentList.getCurrentList();
        Collections.sort(enrolments);

        viewAll(enrolments);

        System.out.print("Choose an enrolment (#) to delete: ");
        int intPut;
        String input = getInput();
        if(input != null && input.matches("\\d+")) {
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
                case "Y", "y" -> {
                    enrolments.remove(enrolmentToDelete);
                    enrolmentList.setCurrentList(enrolments);
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

    public static void viewOneStudent(ArrayList<Student> students, EnrolmentList enrolmentList) {
        ArrayList<StudentEnrolment> found;
        System.out.println("Input StudentID: ");
        String studentID = getInput();
        System.out.println("Input Semester: ");
        String semester = getInput();

        boolean studentExists = false;

        for(Student student : students) {
            if(student.getId().equals(studentID)) {
                studentExists = true;
                break;
            }
        }

        if(!studentExists) {
            System.out.println("Invalid studentID!");
            waitFor(2000);
            return;
        }

        found = enrolmentList.getOne(studentID, semester);
        if(found.isEmpty()) {
            System.out.printf("No enrolments for student under the ID [%s] in the [%s] semester found\n", studentID, semester);
            System.out.println("Returning to Menu...");
            waitFor(2000);
        } else {
            System.out.printf("Found %s enrolment(s) for student under the ID [%s] in the [%s] semester.\n", found.size(), studentID, semester);
            enrolmentList.setCurrentList(found);
            enrolmentList.setCurrentListType("student");
            viewAll(found);
            System.out.println("List selected. You can update and delete from this this using [3] and [4] options.");
            waitFor(2000);
        }

    }

    public static void viewOneCourse(ArrayList<Course> courses, EnrolmentList enrolmentList) {
        ArrayList<StudentEnrolment> found = new ArrayList<>();
        System.out.println("Input CourseID: ");
        String courseID = getInput();
        System.out.println("Input Semester: ");
        String semester = getInput();

        boolean courseExists = false;

        for (Course course : courses) {
            if(course.getId().equals(courseID)){
                courseExists = true;
                break;
            }
        }

        if(!courseExists) {
            System.out.println("Invalid courseID!");
            waitFor(2000);
            return;
        }
        enrolmentList.getAll().forEach(e -> {
            if(e.getCourse().getId().equals(courseID) && e.getSemester().equals(semester)){
                found.add(e);
            };
        });

        if(found.isEmpty()) {
            System.out.printf("No enrolments in [%s] course for the [%s] semester found\n", courseID, semester);
            System.out.println("Returning to Menu...");
            waitFor(2000);
        } else {
            System.out.printf("Found %s enrolment(s) in the [%s] course for the [%s] semester.\n", found.size(), courseID, semester);
            enrolmentList.setCurrentList(found);
            enrolmentList.setCurrentListType("course");
            viewAll(found);
            System.out.println("List selected. You can update and delete from this this using [3] and [4] options.");
            waitFor(2000);
        }
    }

    public static void viewOneSemester(EnrolmentList enrolmentList) {
        ArrayList<StudentEnrolment> found = new ArrayList<>();
        System.out.println("Input Semester: ");
        String input = getInput();

        enrolmentList.getAll().forEach(e -> {
           if(e.getSemester().equals(input)){
               found.add(e);
           };
        });

        if(found.isEmpty()) {
            System.out.println("No enrolments of the [" + input + "] semester found");
            System.out.println("Returning to Menu...");
            waitFor(2000);
        } else {
            System.out.printf("Found %s enrolment(s) from the [%s] semester.\n", found.size(), input);
            enrolmentList.setCurrentList(found);
            enrolmentList.setCurrentListType("semester");
            viewAll(found);
            System.out.println("List selected. You can update and delete from this this using [3] and [4] options.");
            waitFor(2000);
        }
    }

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

    public static boolean addEnrolmentToList(StudentEnrolment enrolment, EnrolmentList enrolmentList){
        boolean enrolmentExists = false;
        for (StudentEnrolment enrolmentToCompare : enrolmentList.getAll()) {
            if(enrolmentToCompare.equals(enrolment)){
                enrolmentExists = true;
                break;
            }
        }
        if (!enrolmentExists) {
            enrolmentList.add(enrolment);
            System.out.printf("Enrolled [%s] into [%s] for the [%s] semester\n", enrolment.getStudent().getName(), enrolment.getCourse().getName(), enrolment.getSemester());
            waitFor(2000);
            return true;
        } else {
            System.out.println("ERROR: Enrolment already exists!");
            waitFor(2000);
            return false;
        }
    }

    public static void unSetList(EnrolmentList enrolmentList){
        enrolmentList.setCurrentListType("none");
        System.out.println("Selected list reset.");
        waitFor(2000);
    }
}
