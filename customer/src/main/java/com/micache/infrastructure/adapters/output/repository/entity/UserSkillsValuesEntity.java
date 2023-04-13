package com.micache.infrastructure.adapters.output.repository.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_skills_values")
public class UserSkillsValuesEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private UUID idUser;
    @NotNull
    private float teamwork;
    @NotNull
    private float selfconfidence;
    @NotNull
    private float proactivity;
    @NotNull
    private float integrity;
    @NotNull
    private float flexibility;
    @NotNull
    private float average;
    @NotNull
    private Date createdAt;
    @NotNull
    private Date updatedAt;
}
