package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty facultyUpdate = facultyRepository.findById(id).orElse(null);
        if (facultyUpdate != null) {
            facultyUpdate.setName(faculty.getName());
            facultyUpdate.setColor(faculty.getColor());
            return facultyRepository.save(facultyUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);

    }

    @Override
    public List<Faculty> getByColor(String color) {
        return facultyRepository.findByColor(color);
    }
}
