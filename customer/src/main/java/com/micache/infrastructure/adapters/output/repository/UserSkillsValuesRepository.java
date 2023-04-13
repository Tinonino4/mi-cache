package com.micache.infrastructure.adapters.output.repository;

import com.micache.domain.model.UserSkillsValues;
import com.micache.infrastructure.adapters.output.repository.entity.UserSkillsValuesEntity;
import com.micache.security.jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserSkillsValuesRepository extends JpaRepository<UserSkillsValuesEntity, UUID> {
}
