package com.consoleapp.student;

public class Student {
    private String id;
    private String name;
    private String birthdate;

    private Student(StudentBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.birthdate = builder.birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "src.java.com.consoleapp.student.Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }

    public static class StudentBuilder {
        private final String id;
        private final String name;
        private String birthdate;

        public StudentBuilder(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public StudentBuilder birthdate(String birthdate) {
            this.birthdate = birthdate;
            return this;
        }
        public Student build() {
            Student student = new Student(this);
            validateStudentObject(student);
            return student;
        }
        private void validateStudentObject(Student student) {

        }
    }
}
