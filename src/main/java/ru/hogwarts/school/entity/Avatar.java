package ru.hogwarts.school.entity;

import javax.persistence.*;

@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] data;
    @OneToOne
    private Student student;
    public Avatar() {
    }

}
