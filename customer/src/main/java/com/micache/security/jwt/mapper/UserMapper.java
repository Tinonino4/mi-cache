package com.micache.security.jwt.mapper;

import com.micache.domain.model.User;
import com.micache.infrastructure.adapters.input.rest.model.RegisterRequest;
import com.micache.security.jwt.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toUserEntityFromUser(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .active(user.isActive())
                .birthday(user.getBirthday())
                .city(user.getCity())
                .pid(user.getPid())
                .created_at(user.getCreated_at())
                .role(user.getRole())
                .education(user.getEducation())
                .password(user.getPassword()).
                build();
    }
    public User toUserFromUserEntity(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .active(userEntity.isActive())
                .birthday(userEntity.getBirthday())
                .city(userEntity.getCity())
                .pid(userEntity.getPid())
                .created_at(userEntity.getCreated_at())
                .role(userEntity.getRole())
                .education(userEntity.getEducation())
                .password(userEntity.getPassword()).
                build();
    }

    public User toUserFromRegisterRequest(RegisterRequest request) {
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword()).build();
    }
}
