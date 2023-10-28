package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        logger.info("Был вызван метод add");
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        logger.info("Был вызван метод get");
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        logger.info("Был вызван метод update");
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        logger.info("Был вызван метод delete");
        studentRepository.deleteById(id);

    }

    @Override
    public List<Student> getByAge(int age) {
        logger.info("Был вызван метод getByAge");
        return studentRepository.findByAge(age);
    }
    @Override
    public List<Student> getByAgeBetween(int min, int max) {
        logger.info("Был вызван метод getByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }
    @Override
    public Faculty getFacultyByStudentId(Long id) {
        logger.info("Был вызван метод getFacultyByStudentId");
        return studentRepository.findById(id).get().getFaculty();
    }
    @Override
    public List<Student> getByFaculty(Long facultyId) {
        logger.info("Был вызван метод getByFaculty");
        return studentRepository.findByFacultyId(facultyId);
    }

    @Override
    public Integer getCount() {
        logger.info("Был вызван метод getCount");
        return studentRepository.getCount();
    }

    @Override
    public Double getAvgAges() {
        logger.info("Был вызван метод getAvgAges");
        return studentRepository.getAvgAge();
    }

    @Override
    public List<Student> getLastFive() {
        logger.info("Был вызван метод getLastFive");
        return studentRepository.getLastFive();
    }

}
