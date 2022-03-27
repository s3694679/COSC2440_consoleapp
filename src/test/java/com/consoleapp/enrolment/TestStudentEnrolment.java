package com.consoleapp.enrolment;

import com.consoleapp.course.Course;
import com.consoleapp.student.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class TestStudentEnrolment {
    String testSemester = "2022A";
    String newSemester = "2021C";
    Student stu1 = new Student
            .StudentBuilder("s0000001", "Toan Duong")
            .birthdate("01/08/1999")
            .build();
    Student stu2 = new Student
            .StudentBuilder("s0000002", "Duong Toan")
            .birthdate("08/01/9991")
            .build();
    Course cou1 = new Course("COSC4030", "Theory of Computation", "5");
    Course cou2 = new Course("BUS2232", "Business Law", "3");
    StudentEnrolment enrol1 = new StudentEnrolment(stu1, cou1, testSemester);
    StudentEnrolment enrol2 = new StudentEnrolment(stu2, cou1, testSemester);
    StudentEnrolment enrol3 = new StudentEnrolment(stu1, cou1, newSemester);

    @After
    public void tearDown() throws Exception {
        enrol1 = new StudentEnrolment(stu1, cou1, testSemester);
        enrol2 = new StudentEnrolment(stu1, cou1, testSemester);
        enrol3 = new StudentEnrolment(stu1, cou1, newSemester);
    }

    @Test
    public void testGetStudent() {
        System.out.println("testGetStudent");
        assertEquals(stu1, enrol1.getStudent());
    }

    @Test
    public void testSetStudent() {
        System.out.println("testSetStudent");
        enrol1.setStudent(stu2);
        assertEquals(stu2, enrol1.getStudent());
    }

    @Test
    public void testGetCourse() {
        System.out.println("testGetCourse");
        assertEquals(cou1, enrol1.getCourse());
    }

    @Test
    public void testSetCourse() {
        System.out.println("testSetCourse");
        enrol1.setCourse(cou2);
        assertEquals(cou2, enrol1.getCourse());
    }

    @Test
    public void testGetSemester() {
        System.out.println("testGetSemester");
        assertEquals(testSemester, enrol1.getSemester());
    }

    @Test
    public void testSetSemester() {
        System.out.println("testSetSemester");
        enrol1.setSemester(newSemester);
        assertEquals(newSemester,enrol1.getSemester());
    }

    @Test
    public void testToString() {
        System.out.println("testToString");
        assertEquals("Enrolment:" + enrol1.getStudent().getName() + " is in " + enrol1.getCourse().getName() + " for the " + enrol1.getSemester() + " semester.", enrol1.toString());
    }

    @Test
    public void testEquals() {
        System.out.println("testEquals");
        StudentEnrolment sameEnrol = new StudentEnrolment(stu1, cou1, testSemester);
        assertTrue(enrol1.equals(sameEnrol));
        assertFalse(enrol1.equals(enrol3));
    }

    @Test
    public void testHashCode() {
        System.out.println("testHashCode");
        int hash = Objects.hash(enrol1.getCourse(), enrol1.getStudent(), enrol1.getSemester());
        assertEquals(hash, enrol1.hashCode());
    }

    @Test
    public void testCompareTo() {
        System.out.println("testCompareTo");
        StudentEnrolment sameEnrol = new StudentEnrolment(stu1, cou1, testSemester);
        assertEquals(-1, enrol1.compareTo(enrol2));
        assertEquals(0, enrol1.compareTo(sameEnrol));
        assertEquals(1, enrol2.compareTo(enrol3));
    }
}