package com.micache.infrastructure.adapters.output.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, UUID> {
}
