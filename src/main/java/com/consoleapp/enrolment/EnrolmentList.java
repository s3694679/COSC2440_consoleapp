package com.consoleapp.enrolment;

import com.consoleapp.student.Student;

import java.util.ArrayList;

public class EnrolmentList implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolmentList = new ArrayList<>();
    private ArrayList<StudentEnrolment> currentList = new ArrayList<>();
    private String currentListType = "none";
    private static EnrolmentList instance = new EnrolmentList();

    private EnrolmentList(){};

    public static EnrolmentList getInstance(){
        return instance;
    }

    public ArrayList<StudentEnrolment> getCurrentList(){
        return this.currentList;
    }

    public void setCurrentList(ArrayList<StudentEnrolment> list){
        this.currentList = list;
    };

    public String getCurrentListType() {
        return currentListType;
    }

    public void setCurrentListType(String currentListType) {
        this.currentListType = currentListType;
    }

    @Override
    public void add(StudentEnrolment enrolment) {
        this.enrolmentList.add(enrolment);
    }

    @Override
    public void update(StudentEnrolment oldEnrol, StudentEnrolment newEnrol) {
        oldEnrol.setCourse(newEnrol.getCourse());
        oldEnrol.setStudent(newEnrol.getStudent());
        oldEnrol.setSemester(newEnrol.getSemester());
    }

    @Override
    public void delete(StudentEnrolment enrolment) {
        this.enrolmentList.remove(enrolment);
    }

    @Override
    public ArrayList<StudentEnrolment> getOne(String studentID, String semester) {
        ArrayList<StudentEnrolment> found = new ArrayList<>();
        enrolmentList.forEach(e -> {
            if(e.getStudent().getId().equals(studentID) && e.getSemester().equals(semester)){
                found.add(e);
            };
        });
        return found;
    }

    @Override
    public ArrayList<StudentEnrolment> getAll() {
        return this.enrolmentList;
    }
}
