package ru.hogwarts.school.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 *Faculty - сцщность
 * <br><i>содержит поля:</i>
 * <br> - id<i> (id факультета)</i>
 * <br> - name<i> (название факультета)</i>
 * <br> - color<i> (цыет факультета)</i>
 * <br> - studentLists<i> (связь {@link Faculty} и {@link Student})</i>
 */

@Entity(name = "faculty")
public class Faculty {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * имя факультета
     */
    private String name;
    /**
     * цвет факультета
     */
    private String color;
    /**
     * связь {@link Faculty} и {@link Student}
     * <br><i> один факультет - много студентов</i>
     */
    @OneToMany
    private List<Student> studentLists;

    public Faculty() {
    }

    public Faculty(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
