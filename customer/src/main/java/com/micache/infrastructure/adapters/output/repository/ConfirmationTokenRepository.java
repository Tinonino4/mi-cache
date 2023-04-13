package com.micache.infrastructure.adapters.output.repository;

import com.micache.infrastructure.adapters.output.repository.entity.ConfirmationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, UUID> {
    Optional<ConfirmationTokenEntity> findByIdUserAndConfirmationToken(UUID idUser, UUID token);
}
