package com.consoleapp.enrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    public void add (StudentEnrolment enrolment);
    public void update(StudentEnrolment oldEnrol, StudentEnrolment newEnrol);
    public void delete (StudentEnrolment enrolment);
    public ArrayList<StudentEnrolment> getOne(String studentID, String semester);
    public ArrayList<StudentEnrolment> getAll();
}
