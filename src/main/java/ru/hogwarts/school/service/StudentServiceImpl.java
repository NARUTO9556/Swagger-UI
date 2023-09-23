package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private Long countId = 0L;
    @Override
    public Student add(Student student) {
        Long id = ++countId;
        student.setId(countId);
        students.put(countId, student);
        return students.get(id);
    }

    @Override
    public Student get(Long id) {
        return students.get(id);
    }

    @Override
    public Student update(Long id, Student student) {
        if (students.containsKey(id)) {
            Student studentById = students.get(id);
            studentById.setName(student.getName());
            students.put(id, studentById);
            return students.get(id);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        students.remove(id);

    }

    @Override
    public List<Student> getByAge(int age) {
        return students.values()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }
}
