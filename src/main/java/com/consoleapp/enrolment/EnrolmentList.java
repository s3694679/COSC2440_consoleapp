package com.consoleapp.enrolment;

import java.util.ArrayList;

public class EnrolmentList implements StudentEnrolmentManager{
    private ArrayList<StudentEnrolment> enrolmentList = new ArrayList<>();
    private static EnrolmentList instance = new EnrolmentList();

    private EnrolmentList(){};

    public static EnrolmentList getInstance(){
        return instance;
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
    public void getOne(StudentEnrolment target) {
//
    }

    @Override
    public ArrayList<StudentEnrolment> getAll() {
        return this.enrolmentList;
    }
}
