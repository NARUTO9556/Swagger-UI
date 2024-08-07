package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.entity.Avatar;

import java.util.Optional;

/**
 * Репозиторий для аватаров
 */

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);
}
