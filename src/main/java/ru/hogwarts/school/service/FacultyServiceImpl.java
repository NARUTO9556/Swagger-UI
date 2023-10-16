package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;
    private final StudentService studentService;

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    @Override
    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);

    }

    @Override
    public List<Faculty> getByColor(String color) {
        return facultyRepository.findByColor(color);
    }
    @Override
    public Set<Faculty> getByColorOrName(String param) {
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findByColorIgnoreCase(param));
        result.addAll(facultyRepository.findByNameIgnoreCase(param));
        return result;
    }
    @Override
    public List<Student> getStudentsByFaculty(Long id) {
        return studentService.getByFaculty(id);
    }
}
