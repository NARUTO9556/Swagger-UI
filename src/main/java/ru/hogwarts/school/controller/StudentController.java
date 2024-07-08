package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Студенты")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод учащихся"
    )

    @GetMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student student = studentService.get(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            tags = "Студенты",
            summary = "Добавление учащегося"
    )

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student addStudent = studentService.add(student);
        return ResponseEntity.status(HttpStatus.OK).body(addStudent);
    }

    @Operation(
            tags = "Студенты",
            summary = "Обновление данных учащегося"
    )

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student updated = studentService.update(id, student);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            tags = "Студенты",
            summary = "Удаление учащегося"
    )

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод учащегося по возврасту"
    )

    @GetMapping
    public List<Student> getByAge(@RequestParam("age") int age) {
        return studentService.getByAge(age);
    }

    @Operation(
            tags = "Студенты",
            summary = "Выво учащегося по возрасту в определенных рамках"
    )

    @GetMapping("/by-age-between")
    public List<Student> getByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getByAgeBetween(min, max);
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод факультета по id студента"
    )

    @GetMapping("/faculty-by-student-id")
    public Faculty getFacultyByStudentId(@RequestParam Long id) {
        return studentService.getFacultyByStudentId(id);
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод счетчика учащихся"
    )

    @GetMapping("/count")
    public Integer getCount() {
        return studentService.getCount();
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод среднего возвраста учащихся"
    )

    @GetMapping("/avg-age")
    public Double getAvgAges() {
        return studentService.getAvgAges();
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод последних пяти учащихся из базы данных"
    )

    @GetMapping("/last-five")
    public List<Student> getLastFive() {
        return studentService.getLastFive();
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод учащихся, фамилия которая начинается с заданного значения"
    )

    @GetMapping("/getStudentIsStartedFromA")
    public ResponseEntity<Collection<String>> getStudentIsStartedFromA() {
        return ResponseEntity.ok(studentService.getStudentIsStartedFromA());
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод среднего возвраста всех учащихся"
    )

    @GetMapping("/getAvgAgeOfAllStudents")
    public ResponseEntity<Double> getAvgAgeOfAllStudents() {
        return ResponseEntity.ok(studentService.getAvgAgeOfAllStudents());
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод списка одним потоком"
    )

    @GetMapping("/list-is-console-threads")
    public ResponseEntity<String> getListStudentsIsConsoleWithThreads() {
        studentService.getListStudentsIsConsoleWithThreads();
        return ResponseEntity.ok("Результат в консоли");
    }

    @Operation(
            tags = "Студенты",
            summary = "Вывод списка в синхронизированном потоке"
    )

    @GetMapping("/list-is-console-synchronized-threads")
    public ResponseEntity<String> getListStudentsIsConsoleWithSynchronizedThreads() {
        studentService.getListStudentsIsConsoleWithSynchronizedThreads();
        return ResponseEntity.ok("Результат в консоли");
    }
}
