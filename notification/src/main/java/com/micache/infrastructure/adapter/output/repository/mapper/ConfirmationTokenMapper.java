package com.micache.infrastructure.adapter.output.repository.mapper;

import com.micache.domain.ConfirmationToken;
import com.micache.infrastructure.adapter.output.repository.entity.ConfirmationTokenEntity;
import org.springframework.stereotype.Component;

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
