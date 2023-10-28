package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

}
