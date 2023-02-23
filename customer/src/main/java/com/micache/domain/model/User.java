package com.micache.domain.model;

import com.micache.domain.exception.InvalidEmailException;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean active;

    private Date created_at;
    private Date updated_at;
    private String pid;
    @ToString.Exclude private byte[] photo;
    private String city;
    private String birthday;
    private String zipcode;
    private String phone_number;
    private String job_title;
    private String education;

    public boolean isEmailValid() {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(this.email).matches()) return true;
        throw new InvalidEmailException("Invalid Email");
    }
}
