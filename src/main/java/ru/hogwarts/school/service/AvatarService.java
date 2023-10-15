package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long studentId);

    ResponseEntity<byte[]> downloadAvatarByStudentFromDb(Long studentId);

    void downloadAvatarFromFileSystem(Long studentId, HttpServletResponse response) throws IOException;
}
