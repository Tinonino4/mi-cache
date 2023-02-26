package com.micache.application.usecases.mapper;

import com.micache.domain.model.ConfirmationToken;
import org.springframework.stereotype.Component;
import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;

@Component
public class ConfirmationTokenMapper {
    public ConfirmationTokenEntity toConfirmationTokenEntity(ConfirmationToken confirmationToken) {
        return ConfirmationTokenEntity.builder()
                .id(confirmationToken.getId())
                .idUser(confirmationToken.getIdUser())
                .confirmationToken(confirmationToken.getConfirmationToken())
                .createdAt(confirmationToken.getCreatedAt())
                .build();
    }

    public ConfirmationToken toConfirmationToken(ConfirmationTokenEntity confirmationTokenEntity) {
        return ConfirmationToken.builder()
                .id(confirmationTokenEntity.getId())
                .idUser(confirmationTokenEntity.getIdUser())
                .confirmationToken(confirmationTokenEntity.getConfirmationToken())
                .createdAt(confirmationTokenEntity.getCreatedAt())
                .build();
    }
}
