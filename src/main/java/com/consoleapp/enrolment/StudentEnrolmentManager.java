package com.consoleapp.enrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    public void add (StudentEnrolment enrolment);
    public void update(StudentEnrolment oldEnrol, StudentEnrolment newEnrol);
    public void delete (StudentEnrolment enrolment);
    public void getOne(StudentEnrolment target);
    public ArrayList<StudentEnrolment> getAll();
}
