package ru.hogwarts.school.service;

import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import java.util.List;
import java.util.Set;

public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty get(Long id);
    Faculty update(Long id, Faculty faculty);
    void delete(Long id);
    List<Faculty> getByColor(String color);
    Set<Faculty> getByColorOrName(String param);
    List<Student> getStudentsByFaculty(Long id);
}
