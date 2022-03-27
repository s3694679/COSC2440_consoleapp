package com.consoleapp.enrolment;

import com.consoleapp.course.Course;
import com.consoleapp.student.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestEnrolmentList {
    String testStuId = "s0000001";
    String testSemester = "2022A";
    Student stu1 = new Student
            .StudentBuilder(testStuId, "Toan Duong")
            .birthdate("01/08/1999")
            .build();
    Course cou1 = new Course("COSC4030", "Theory of Computation", "5");
    Course cou2 = new Course("BUS2232", "Business Law", "3");
    StudentEnrolment enrol1 = new StudentEnrolment(stu1, cou1, testSemester);
    StudentEnrolment enrol2 = new StudentEnrolment(stu1, cou2, testSemester);
    StudentEnrolment enrol3 = new StudentEnrolment(stu1, cou1, "2022B");

    EnrolmentList enrolmentList = EnrolmentList.getInstance();
    ArrayList<StudentEnrolment> testList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        testList.addAll(Arrays.asList(enrol1, enrol2));
        enrolmentList.add(enrol1);
    }

    @After
    public void tearDown() throws Exception {
        ArrayList<StudentEnrolment> emptyList = new ArrayList<>();
        testList = emptyList;
        enrolmentList.setCurrentListType("none");
        enrolmentList.setCurrentList(emptyList);
        enrolmentList.delete(enrol1);
        enrolmentList.delete(enrol2);
        enrolmentList.delete(enrol3);

    }

    @Test
    public void testGetInstance() {
        EnrolmentList instance1 = EnrolmentList.getInstance();
        EnrolmentList instance2 = EnrolmentList.getInstance();
        assertSame("2 are the same", instance1, instance2);
    }

    @Test
    public void testGetCurrentList() {
        System.out.println("testGetCurrentList");
        assertTrue(enrolmentList.getCurrentList().isEmpty());
    }

    @Test
    public void testSetCurrentList() {
        System.out.println("testSetCurrentList");
        enrolmentList.setCurrentList(testList);
        assertEquals(testList,enrolmentList.getCurrentList());

    }

    @Test
    public void testGetCurrentListType() {
        System.out.println("testGetCurrentListType");
        assertEquals("none", enrolmentList.getCurrentListType());
    }

    @Test
    public void testSetCurrentListType() {
        System.out.println("testSetCurrentListType");
        String test = "test";
        enrolmentList.setCurrentListType(test);
        assertEquals("test",enrolmentList.getCurrentListType());
    }

    @Test
    public void testAdd() {
        System.out.println("testAdd");
        enrolmentList.add(enrol2);
        assertEquals(testList, enrolmentList.getAll());
    }

    @Test
    public void testUpdate() {
        System.out.println("testUpdate");
        enrolmentList.update(enrol1,enrol2);
        assertEquals(enrol2, enrolmentList.getAll().get(0));
    }

    @Test
    public void testDelete() {
        System.out.println("testDelete");
        enrolmentList.delete(enrol1);
        assertTrue(enrolmentList.getAll().isEmpty());
    }

    @Test
    public void testGetOne() {
        System.out.println("testGetOne");
        enrolmentList.add(enrol2);
        enrolmentList.add(enrol3);
        ArrayList<StudentEnrolment> stu1Enrolments = new ArrayList<>(Arrays.asList(enrol1, enrol2));
        assertEquals(stu1Enrolments, enrolmentList.getOne(testStuId, testSemester));
    }

    @Test
    public void testGetAll() {
        testList.remove(enrol2);
        assertEquals(testList, enrolmentList.getAll());
    }
}