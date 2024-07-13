package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private Integer count = 0;

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

    @Override
    public List<String> getStudentIsStartedFromA() {
        logger.info("Был вызван метод getStudentIsStartedFromA");
        return studentRepository.findAll().stream().
                map(Student::getName).
                filter(names -> names.startsWith("A")).
                sorted().collect(Collectors.toList());
    }

    @Override
    public double getAvgAgeOfAllStudents() {
        logger.info("Был вызван метод getAvgAgeOfAllStudents");
        return studentRepository.findAll().stream().
                mapToDouble(Student::getAge).
                average().orElse(Double.NaN);
    }
    @Override
    public void getListStudentsIsConsoleWithThreads() {
        logger.info("Был вызван метод getListStudentsIsConsoleWithThreads");
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList.get(0).getName());
        System.out.println(studentList.get(1).getName());
        new Thread(() -> {
            System.out.println(studentList.get(2).getName());
            System.out.println(studentList.get(3).getName());
        }).start();
        new Thread(() -> {
            System.out.println(studentList.get(4).getName());
            System.out.println(studentList.get(5).getName());
        }).start();
    }

    @Override
    public void getListStudentsIsConsoleWithSynchronizedThreads() {
        logger.info("Был вызван метод getListStudentsIsConsoleWithSynchronizedThreads");
        List<Student> studentList = studentRepository.findAll();
        printStudentName(studentList);
        printStudentName(studentList);
        new Thread(() -> {
            printStudentName(studentList);
            printStudentName(studentList);
        }).start();
        new Thread(() -> {
            printStudentName(studentList);
            printStudentName(studentList);
        }).start();
    }

    private synchronized void printStudentName(List<Student> studentList) {
        System.out.println(studentList.get(count).getName());
        count++;
    }
}
