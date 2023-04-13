package com.micache.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSkillsValues {
    private UUID id;
    private UUID idUser;
    private float teamwork;
    private float selfconfidence;
    private float proactivity;
    private float integrity;
    private float flexibility;
    private float average;
    private Date createdAt;
    private Date updatedAt;

    public UserSkillsValues(UUID idUser) {
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
