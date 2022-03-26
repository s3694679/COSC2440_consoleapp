package com.consoleapp.misc;

import com.consoleapp.course.Course;
import com.consoleapp.enrolment.*;
import com.consoleapp.student.Student;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

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

    public static void printMenu(String[] menu){
        for (String option : menu){
            System.out.println(option);
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

    public static void unSetList(EnrolmentList enrolmentList){
        enrolmentList.setCurrentListType("none");
        System.out.println("Selected list reset.");
        waitFor(2000);
    }

    public static boolean writeToCSV(String filePath, ArrayList<StudentEnrolment> enrolments) {
        File file = new File(filePath);
        System.out.println("Path: [" + file.getAbsolutePath() + "]");
        try {
            FileWriter output = new FileWriter(file);
            CSVWriter writer = new CSVWriter(output);

            for (StudentEnrolment enrolment : enrolments) {
                Student student = enrolment.getStudent();
                Course course = enrolment.getCourse();
                String[] line = {
                        student.getId(),
                        student.getName(),
                        student.getBirthdate(),
                        course.getId(),
                        course.getName(),
                        course.getCredits(),
                        enrolment.getSemester()};
                writer.writeNext(line);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

