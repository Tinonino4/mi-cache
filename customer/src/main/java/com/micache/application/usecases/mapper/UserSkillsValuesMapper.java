package com.micache.application.usecases.mapper;

import com.micache.domain.model.ConfirmationToken;
import com.micache.domain.model.UserSkillsValues;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;
import com.micache.infrastructure.adapters.output.repository.entity.UserSkillsValuesEntity;
import org.springframework.stereotype.Component;

@Component
public class UserSkillsValuesMapper {
    public UserSkillsValuesEntity toUserSkillsValuesEntity(UserSkillsValues userSkillsValues) {
        return UserSkillsValuesEntity.builder()
                .id(userSkillsValues.getId())
                .idUser(userSkillsValues.getIdUser())
                .average(userSkillsValues.getAverage())
                .flexibility(userSkillsValues.getFlexibility())
                .proactivity(userSkillsValues.getProactivity())
                .createdAt(userSkillsValues.getCreatedAt())
                .integrity(userSkillsValues.getIntegrity())
                .selfconfidence(userSkillsValues.getSelfconfidence())
                .teamwork(userSkillsValues.getTeamwork())
                .updatedAt(userSkillsValues.getUpdatedAt())
                .build();
    }

    public UserSkillsValues toUserSkillsValues(UserSkillsValuesEntity userSkillsValuesEntity) {
        return UserSkillsValues.builder()
                .id(userSkillsValuesEntity.getId())
                .idUser(userSkillsValuesEntity.getIdUser())
                .average(userSkillsValuesEntity.getAverage())
                .flexibility(userSkillsValuesEntity.getFlexibility())
                .proactivity(userSkillsValuesEntity.getProactivity())
                .createdAt(userSkillsValuesEntity.getCreatedAt())
                .integrity(userSkillsValuesEntity.getIntegrity())
                .selfconfidence(userSkillsValuesEntity.getSelfconfidence())
                .teamwork(userSkillsValuesEntity.getTeamwork())
                .updatedAt(userSkillsValuesEntity.getUpdatedAt())
                .build();
    }
}
