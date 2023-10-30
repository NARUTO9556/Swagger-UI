package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;
    private final StudentService studentService;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentService = studentService;
    }

    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Был вызван метод add");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        logger.info("Был вызван метод get");
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        logger.info("Был вызван метод update");
        return facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        logger.info("Был вызван метод delete");
        facultyRepository.deleteById(id);

    }

    @Override
    public List<Faculty> getByColor(String color) {
        logger.info("Был вызван метод getByColor");
        return facultyRepository.findByColor(color);
    }
    @Override
    public Set<Faculty> getByColorOrName(String param) {
        logger.info("Был вызван метод getByColorOrName");
        Set<Faculty> result = new HashSet<>();
        result.addAll(facultyRepository.findByColorIgnoreCase(param));
        result.addAll(facultyRepository.findByNameIgnoreCase(param));
        return result;
    }
    @Override
    public List<Student> getStudentsByFaculty(Long id) {
        logger.info("Был вызван метод getStudentsByFaculty");
        return studentService.getByFaculty(id);
    }

    @Override
    public String getLongestFacultyName() {
        logger.info("Был вызван метод getLongestFacultyName");
        return facultyRepository.findAll().stream().
                map(Faculty::getName).
                max(Comparator.comparingInt(String::length)).
                orElseThrow();
    }
}
