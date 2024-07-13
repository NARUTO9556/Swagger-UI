package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    private StudentRepository studentRepository;
    private AvatarRepository avatarRepository;
    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public AvatarServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Был вызван метод uploadAvatar");
        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarsDir,  studentId + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = avatarRepository.findByStudentId(studentId).orElseGet(Avatar::new);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }
    @Override
    public Avatar findAvatar(Long studentId) {
        logger.info("Был вызван метод findAvatar");
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }
    private String getExtensions(String fileName) {
        logger.info("Был вызван метод getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    @Override
    public ResponseEntity<byte[]> downloadAvatarByStudentFromDb(Long studentId) {
        logger.info("Был вызван метод downloadAvatarByStudentFromDb");

        Optional<Avatar> avatarOpt = avatarRepository.findByStudentId(studentId);

        if (avatarOpt.isEmpty()) {
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }

        Avatar avatar = avatarOpt.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
    @Override
    public void downloadAvatarFromFileSystem(Long studentId, HttpServletResponse response) throws IOException {
        logger.info("Был вызван метод downloadAvatarFromFileSystem");

        Optional<Avatar> avatarOpt = avatarRepository.findByStudentId(studentId);

        if (avatarOpt.isEmpty()) {
            return;
        }

        Avatar avatar = avatarOpt.get();

        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @Override
    public Page<Avatar> getWithPageable(Integer page, Integer count) {
        logger.info("Был вызван метод getWithPageable");
        return avatarRepository.findAll(PageRequest.of(page, count));
    }
}
