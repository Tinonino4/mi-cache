package com.micache.infrastructure.adapter.output.repository;

import com.micache.infrastructure.adapter.output.repository.entity.ConfirmationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, UUID> {
}
