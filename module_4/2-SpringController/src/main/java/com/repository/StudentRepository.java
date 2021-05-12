package com.repository;

import com.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAll();
    Student getStudentById(String id);
    void deleteStudentById(String id);
    void updateStudent(Student student);
    void addNewStudent(Student student);
}
