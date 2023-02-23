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
    User toUserFromUserEntity(UserEntity userEntity) {
        return null;
    }

    public User toUserFromRegisterRequest(RegisterRequest request) {
        return User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword()).build();
    }
}
