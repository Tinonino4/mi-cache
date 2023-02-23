package com.micache.domain.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserSkillsValues {
    private UUID id;
    private Long idUser;
    private float teamwork;
    private float selfconfidence;
    private float proactivity;
    private float integrity;
    private float flexibility;
    private float average;
    private Date createdAt;
    private Date updatedAt;

    public UserSkillsValues(Long idUser) {
        this.idUser = idUser;
        this.teamwork = 0;
        this.selfconfidence = 0;
        this.proactivity = 0;
        this.integrity = 0;
        this.flexibility = 0;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
