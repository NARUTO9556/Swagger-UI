package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.service.InfoService;
import ru.hogwarts.school.service.StudentService;

import java.util.stream.Stream;

@Service
public class InfoServiceImpl implements InfoService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Value("${server.port}")
    private String currentPort;
    @Override
    public String getCurrentPort() {
        logger.info("Был вызван метод getCurrentPort");
        return currentPort;
    }

    @Override
    public Integer getSum() {
        logger.info("Был вызван метод getSum");
        return Stream.iterate(1, a -> a + 1).
                limit(1_000_000).
                reduce(0, Integer::sum);
    }
}
