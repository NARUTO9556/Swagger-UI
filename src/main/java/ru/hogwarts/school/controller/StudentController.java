package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = studentService.get(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student addStudent = studentService.add(student);
        return ResponseEntity.status(HttpStatus.OK).body(addStudent);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.update(id, student);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Student> getByAge(@RequestParam("age") int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/by-age-between")
    public List<Student> getByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getByAgeBetween(min, max);
    }

    @GetMapping("/faculty-by-student-id")
    public Faculty getFacultyByStudentId(@RequestParam Long id) {
        return studentService.getFacultyByStudentId(id);
    }
    @GetMapping("/count")
    public Integer getCount() {
        return studentService.getCount();
    }
    @GetMapping("/avg-age")
    public Double getAvgAges() {
        return studentService.getAvgAges();
    }
    @GetMapping("/last-five")
    public List<Student> getLastFive() {
        return studentService.getLastFive();
    }
    @GetMapping("/getStudentIsStartedFromA")
    public ResponseEntity<Collection<String>> getStudentIsStartedFromA() {
        return ResponseEntity.ok(studentService.getStudentIsStartedFromA());
    }

    @GetMapping("/getAvgAgeOfAllStudents")
    public ResponseEntity<Double> getAvgAgeOfAllStudents() {
        return ResponseEntity.ok(studentService.getAvgAgeOfAllStudents());
    }
    @GetMapping("/list-is-console-threads")
    public ResponseEntity<String> getListStudentsIsConsoleWithThreads() {
        studentService.getListStudentsIsConsoleWithThreads();
        return ResponseEntity.ok("Результат в консоли");
    }
    @GetMapping("/list-is-console-synchronized-threads")
    public ResponseEntity<String> getListStudentsIsConsoleWithSynchronizedThreads() {
        studentService.getListStudentsIsConsoleWithSynchronizedThreads();
        return ResponseEntity.ok("Результат в консоли");
    }
}
