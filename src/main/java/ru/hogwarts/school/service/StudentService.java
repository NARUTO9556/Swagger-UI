package ru.hogwarts.school.service;

import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import java.util.List;

public interface StudentService {
    Student add(Student student);
    Student get(Long id);
    Student update(Long id, Student student);
    void delete(Long id);
    List<Student> getByAge(int age);
    List<Student> getByAgeBetween(int min, int max);
    Faculty getFacultyByStudentId(Long id);
    List<Student> getByFaculty(Long id);
}
