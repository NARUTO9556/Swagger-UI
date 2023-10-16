package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    void get() throws Exception{
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/students/1", String.class),
                "{\"id\":1,\"name\":\"Иван\",\"age\":22,\"faculty\":null,\"avatar\":null}");
    }

    @Test
    void add() throws Exception{
        Student studentForCreate = new Student(1L,"Иван", 22);
        Student expectedStudent = new Student(1L,"Иван", 22);

        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/students", studentForCreate, Student.class);
        assertThat(postedStudent).isNotNull();
        assertEquals(expectedStudent.getName(), postedStudent.getName());
        assertEquals(expectedStudent.getAge(), postedStudent.getAge());
    }

    @Test
    void update() throws Exception{
        Student studentTest = new Student(11L,"Гендальф",200);
        restTemplate.put("http://localhost:" + port + "/student/11", studentTest);

        Assertions.assertNotNull(this.restTemplate.postForObject("http://localhost:" + port + "/student", studentTest, String.class));
    }

    @Test
    void delete() throws Exception{
        ResponseEntity<Void> resp = restTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }

    @Test
    void getByAge() throws Exception{
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/students?age=12", String.class),
                "[{\"id\":13,\"name\":\"Боря\",\"age\":12,\"faculty\":null,\"avatar\":null}]");
    }

    @Test
    void getByAgeBetween () throws Exception{
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/students/by-age-between?min=11&max=12", String.class),
            "[{\"id\":13,\"name\":\"Боря\",\"age\":12,\"faculty\":null,\"avatar\":null}]");
    }

    @Test
    void getFacultyByStudentId() throws Exception{
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/students/faculty-by-student-id?id=1", String.class),
            "{\"id\":1,\"name\":\"1A\",\"color\":\"111\",\"studentList\":[]}");

    }
    @Test
    public void contextLoadsFaculty() throws Exception {
        assertThat(facultyController).isNotNull();
    }
    @Test
    public void testGetFacultyInfo() throws Exception {
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/1", String.class),
                "{\"id\":1,\"name\":\"Гриффиндор\",\"color\":\"Красный\"}");
    }
    @Test
    public void testGetFacultyInfoColorOrName() throws Exception {
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/get-color-or-name?str=222", String.class),
                "[{\"id\":2,\"name\":\"2B\",\"color\":\"222\",\"studentList\":[]}]");
    }
    @Test
    public void testGetStudentInfoByFacultyId() throws Exception {
        Assertions.assertEquals(this.restTemplate.getForObject("http://localhost:" + port + "/faculties/students-by-faculty-id?id=1", String.class),
                "[{\"id\":2,\"name\":\"BBB\",\"age\":12,\"faculty\":{\"id\":1,\"name\":\"1A\",\"color\":\"111\",\"studentList\":[]}},{\"id\":1,\"name\":\"string\",\"age\":5,\"faculty\":{\"id\":1,\"name\":\"1A\",\"color\":\"111\",\"studentList\":[]}}]");
    }
    @Test
    public void testPostFaculty() throws Exception{
        Faculty facultyTest = new Faculty();
        facultyTest.setName("Орки");
        facultyTest.setColor("Черный");
        Assertions.assertNotNull(this.restTemplate.postForObject("http://localhost:" + port + "/faculties", facultyTest, String.class));
    }
    @Test
    public void testDeleteFaculty() throws Exception{
        ResponseEntity<Void> resp = restTemplate.exchange("http://localhost:" + port + "/faculties/123", HttpMethod.DELETE, HttpEntity.EMPTY,Void.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,resp.getStatusCode());
    }
    @Test
    public void testPutFaculty() throws Exception{
        Faculty facultyTest = new Faculty(1L, "Хоббиты","Белый");
        restTemplate.put("http://localhost:" + port + "/faculty/4", facultyTest);

        Assertions.assertNotNull(this.restTemplate.postForObject("http://localhost:" + port + "/faculties", facultyTest, String.class));
    }
}
