package com.consoleapp.student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.Objects;

import static org.junit.Assert.*;

public class TestStudent {
    String testId = "s0000001";
    String testName = "Toan Duong";
    String testBirthdate = "01/08/1999";
    Student student = new Student
            .StudentBuilder(testId, testName)
            .birthdate(testBirthdate)
            .build();

    @Test
    public void testGetId() {
        System.out.println("testGetId");
        assertEquals(testId, student.getId());
    }

    @Test
    public void testGetName() {
        System.out.println("testGetName");
        assertEquals(testName, student.getName());
    }

    @Test
    public void testGetBirthdate() {
        System.out.println("testGetBirthdate");
        assertEquals(testBirthdate, student.getBirthdate());
    }


    @Test
    public void testToString() {
        System.out.println("testToString");
        assertEquals("Student ID: '" + testId + "', Name: '" + testName + "', DOB: '" + testBirthdate + "'", student.toString());
    }

    @Test
    public void testEquals() {
        System.out.println("testEquals");
        Student student1 = student;
        Student student2 = new Student
                .StudentBuilder("s0000002", "Duong Toan")
                .birthdate("08/01/9991")
                .build();
        assertTrue(student.equals(student1));
        assertFalse(student.equals(student2));
    }

    @Test
    public void testHashCode() {
        System.out.println("testHashCode");
        int hash = Objects.hash(student.getId(), student.getName(), student.getBirthdate());
        assertEquals(hash, student.hashCode());
    }

    @Test
    public void testCompareTo() {
        System.out.println("testCompareTo");
        Student student1 = student;
        Student student2 = new Student
                .StudentBuilder("s0000002", "Duong Toan")
                .birthdate("08/01/9991")
                .build();
        assertEquals(-1, student1.compareTo(student2));
        assertEquals(0, student.compareTo(student1));
        assertEquals(1, student2.compareTo(student1));

    }
}