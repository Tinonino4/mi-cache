package com.micache.domain.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {
    private UUID id; // PK
    @NotNull
    private UUID idUser; //FK
    @NotNull
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public ConfirmationToken(UUID idUser) {
        this.idUser = idUser;
        this.createdAt = new Date();
        this.confirmationToken = UUID.randomUUID().toString();
    }
}