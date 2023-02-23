package com.micache.domain;

import com.sun.istack.NotNull;
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
public class ConfirmationToken {
    private UUID id; // PK
    @NotNull
    private UUID idUser; //FK
    @NotNull
    private String confirmation_token;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Date created_at;
}