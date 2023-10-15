package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.entity.Student;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    void get() {
        Student studentForCreate = new Student("Иван", 20);

        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        Student actualStudent= this.restTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + postedStudent.getId(), Student.class);
        assertNotNull(actualStudent, "Expected a non-null Student, but got null");
        assertEquals(postedStudent.getName(), actualStudent.getName());
        assertEquals(postedStudent.getAge(), actualStudent.getAge());
    }

    @Test
    void add() {
        Student studentForCreate = new Student("Иван", 22);
        Student expectedStudent = new Student("Иван", 22);

        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        Student actualStudent= this.restTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + postedStudent.getId(), Student.class);
        assertThat(actualStudent).isNotNull();
        assertEquals(expectedStudent.getName(), actualStudent.getName());
        assertEquals(expectedStudent.getAge(), actualStudent.getAge());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getByAge() {
    }

    @Test
    void getByAgeBetween() {
    }

    @Test
    void getFacultyByStudentId() {
    }
}