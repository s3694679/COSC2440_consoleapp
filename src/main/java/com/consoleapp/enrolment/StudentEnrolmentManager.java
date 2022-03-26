package com.consoleapp.enrolment;

import java.util.ArrayList;

public interface StudentEnrolmentManager {
    void add (StudentEnrolment enrolment);
    void update(StudentEnrolment oldEnrol, StudentEnrolment newEnrol);
    void delete (StudentEnrolment enrolment);
    ArrayList<StudentEnrolment> getOne(String studentID, String semester);
    ArrayList<StudentEnrolment> getAll();
}
