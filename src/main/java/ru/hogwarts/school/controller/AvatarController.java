package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
@RestController
@RequestMapping("/avatar")
@Tag(name = "Аватары")
public class AvatarController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public AvatarController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @Operation(
            tags = "Аватары",
            summary = "Загрузка Аватара"
    )

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("Файл слишком большой");
        }
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Аватары",
            summary = "Загрузка Аватара в базу данных"
    )

    @GetMapping(value = "/student/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatarFromDb(@PathVariable Long id) {
        return avatarService.downloadAvatarByStudentFromDb(id);
    }

    @Operation(
            tags = "Аватары",
            summary = "Выгрузка Аватара и базы данных"
    )

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException{
        avatarService.downloadAvatarFromFileSystem(id, response);
    }

    @Operation(
            tags = "Аватары",
            summary = "Отображение аватаров"
    )

    @GetMapping
    public Page<Avatar> getWithPageable(@RequestParam Integer page, @RequestParam Integer count) throws IOException{
        return avatarService.getWithPageable(page, count);
    }
}
