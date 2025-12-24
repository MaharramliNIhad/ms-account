package org.company.msaccount.mapper;

import org.company.msaccount.dao.Users;
import org.company.msaccount.enums.ActiveStatus;
import org.company.msaccount.model.UserRequest;
import org.company.msaccount.model.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
private UserMapper userMapper=UserMapper.INSTANCE;
    @Test
    void toUsers() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("test");
        userRequest.setEmail("test@test.com");
        userRequest.setPassword("test");
        userRequest.setSurname("test");
        Users users1 = userMapper.toUsers(userRequest);
        Users users = new Users();
        users.setName("test");
        users.setEmail("test@test.com");
        users.setPassword("test");
        users.setSurname("test");
        Assertions.assertEquals(users1, users);
    }

    @Test
    void toUserResponse() {
        Users users=new Users();
        users.setName("test");
        users.setEmail("test@test.com");
        users.setSurname("test");
        UserResponse userResponse = userMapper.toUserResponse(users);
        UserResponse userResponse1 = new UserResponse();
        userResponse1.setName("test");
        userResponse1.setEmail("test@test.com");
        userResponse1.setSurname("test");
        Assertions.assertEquals(userResponse, userResponse1);
    }

}