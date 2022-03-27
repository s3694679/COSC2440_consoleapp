package com.consoleapp.course;

import com.consoleapp.student.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class TestCourse {
    String testId = "COSC4030";
    String testName = "Theory of Computation";
    String testCredits = "5";
    String newId = "BUS2232";
    String newName = "Business Law";
    String newCredits = "3";
    Course course = new Course(testId, testName, testCredits);

    @After
    public void tearDown() throws Exception {
        course = new Course(testId, testName, testCredits);
    }

    @Test
    public void testGetId() {
        System.out.println("testGetId");
        assertEquals(testId, course.getId());
    }

    @Test
    public void testSetId() {
        System.out.println("testSetId");
        course.setId(newId);
        assertEquals(newId, course.getId());
    }

    @Test
    public void testGetName() {
        System.out.println("testGetName");
        assertEquals(testName, course.getName());
    }

    @Test
    public void testSetName() {
        System.out.println("testSetName");
        course.setName(newName);
        assertEquals(newName, course.getName());
    }

    @Test
    public void testGetCredits() {
        System.out.println("testGetBirthdate");
        assertEquals(testCredits, course.getCredits());
    }

    @Test
    public void testSetCredits() {
        System.out.println("testSetCredits");
        course.setCredits(newCredits);
        assertEquals(newCredits, course.getCredits());
    }

    @Test
    public void testToString() {
        System.out.println("testToString");
        assertEquals("Course ID: '" + testId +
                ", Name: '" + testName +
                ", Credits: '" + testCredits +
                '}', course.toString());
    }

    @Test
    public void testEquals() {
        System.out.println("testEquals");
        Course course1 = new Course(testId, testName, testCredits);
        Course course2 = new Course(newId, newName, newCredits);
        assertTrue(course1.equals(course));
        assertFalse(course1.equals(course2));
    }

    @Test
    public void testHashCode() {
        System.out.println("testHashCode");
        int hash = Objects.hash(course.getId(), course.getName(), course.getCredits());
        assertEquals(hash, course.hashCode());
    }

    @Test
    public void testCompareTo() {
        System.out.println("testCompareTo");
        Course course1 = new Course(testId, testName, testCredits);
        Course course2 = new Course(newId, newName, newCredits);
        assertEquals(1, course1.compareTo(course2));
        assertEquals(0, course.compareTo(course1));
        assertEquals(-1, course2.compareTo(course1));
    }
}