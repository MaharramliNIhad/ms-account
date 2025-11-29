package org.company.msaccount.mapper;

import org.company.msaccount.dao.Users;
import org.company.msaccount.enums.ActiveStatus;
import org.company.msaccount.model.UserRequest;
import org.company.msaccount.model.UserResponse;

public enum UserMapper {
    INSTANCE;
    public Users toUsers(UserRequest userRequest) {
        return Users.builder().email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .surname(userRequest.getSurname())
                .active(ActiveStatus.ACTIVE)
                .build();

    }
    public UserResponse toUserResponse(Users users) {
        return UserResponse.builder().name(users.getName())
                .surname(users.getSurname())
                .email(users.getEmail()).build();
    }

}
