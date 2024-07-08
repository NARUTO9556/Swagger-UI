package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/faculties")
@Tag(name = "Факультеты")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Operation(
            tags = "Факультеты",
            summary = "Отображение факультета"
    )

    @GetMapping("{id}")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = facultyService.get(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            tags = "Факультеты",
            summary = "Добавление факультета"
    )

    @PostMapping
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty) {
        Faculty addFaculty = facultyService.add(faculty);
        return ResponseEntity.status(HttpStatus.OK).body(addFaculty);
    }

    @Operation(
            tags = "Факультеты",
            summary = "Обновление факультета"
    )

    @PutMapping("{id}")
    public ResponseEntity<Faculty> update(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty updated = facultyService.update(id, faculty);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            tags = "Факультеты",
            summary = "Удаление факультета"
    )

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Факультеты",
            summary = "Вывод учащихся по возврасту"
    )

    @GetMapping
    public List<Faculty> getByAge(@RequestParam("color") String color) {
        return facultyService.getByColor(color);
    }

    @Operation(
            tags = "Факультеты",
            summary = "Вывод учащихся по возврасту или цвету факультета"
    )

    @GetMapping("/by-name-or-color")
    public Set<Faculty> getByColorOrName(@RequestParam String param) {
        return facultyService.getByColorOrName(param);
    }

    @Operation(
            tags = "Факультеты",
            summary = "Вывод учащихся по id факультета"
    )

    @GetMapping("/students-by-faculty-id")
    public List<Student> getStudentsByFacultyId(@RequestParam Long id) {
        return facultyService.getStudentsByFaculty(id);
    }

    @Operation(
            tags = "Факультеты",
            summary = "Вывод факультета по длине названия факультета"
    )

    @GetMapping("/getLongestFacultyName")
    public ResponseEntity<String> getLongestFacultyName() {
        return ResponseEntity.ok(facultyService.getLongestFacultyName());
    }
}
