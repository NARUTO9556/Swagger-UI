package ru.hogwarts.school.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 *Student - сущность
 * <br><i>содержит поля:</i>
 * <br> - id<i> (id студента)</i>
 * <br> - name<i> (имя студента)</i>
 * <br> - age<i> (возраст студента)</i>
 * <br> - faculty<i> (связь {@link Faculty} и {@link Student})</i>
 * <br> - avatar<i> (связь {@link Avatar} и {@link Student})</i>
 */

@Entity(name = "student")
public class Student {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * имя студента
     */
    private String name;
    /**
     * возраст студента
     */
    private int age;
    /**
     * связь {@link Faculty} и {@link Student}
     * <br><i> один факультет - много студентов</i>
     */
    @ManyToOne
    private Faculty faculty;
    /**
     * связь {@link Avatar} и {@link Student}
     * <br><i> один студент - один аватар</i>
     */
    @OneToOne
    private Avatar avatar;

    public Student() {
    }

    public Student(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
