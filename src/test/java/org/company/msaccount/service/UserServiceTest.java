package org.company.msaccount.service;

import org.company.msaccount.dao.Users;
import org.company.msaccount.enums.ActiveStatus;
import org.company.msaccount.exception.UserNotFoundException;
import org.company.msaccount.model.UserRequest;
import org.company.msaccount.model.UserResponse;
import org.company.msaccount.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    private Users user;
    @BeforeEach
    void setUp() {
        user=new Users();
        user.setName("test");
        user.setEmail("test@company.com");
        user.setPassword("test");
        user.setId(1l);
        user.setActive(ActiveStatus.ACTIVE);
        user.setSurname("test");
    }

    @Test
    void saveUser() {
        UserRequest userRequest=new UserRequest();
        userRequest.setEmail("test@company.com");
        userRequest.setPassword("test");
        userRequest.setName("test");
        userRequest.setSurname("test");
        userRequest.setActive(ActiveStatus.ACTIVE.name());
        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);
        Long id=userService.saveUser(userRequest);
        assertEquals(user.getId(),id);
        Mockito.verify(userRepository,Mockito.times(1)).save(Mockito.any(Users.class));
    }

    @Test
    void getUserById() {
        Mockito.when(userRepository.findByIdAndActive(1l,ActiveStatus.ACTIVE)).thenReturn(Optional.of(user));
        UserResponse userResponse=new UserResponse();
        userResponse.setEmail("test@company.com");
        userResponse.setName("test");
        userResponse.setSurname("test");
        Users users=userService.getUserById(1l);
assertEquals(userResponse.getEmail(),users.getEmail());
assertEquals(userResponse.getName(),users.getName());
assertEquals(userResponse.getSurname(),users.getSurname());
Mockito.verify(userRepository,Mockito.times(1)).findByIdAndActive(Mockito.anyLong(),Mockito.any(ActiveStatus.class));

    }
@Test
void getUserByIdNotFound() {
        Mockito.when(userRepository.findByIdAndActive(1l,ActiveStatus.ACTIVE)).thenReturn(Optional.empty());
        UserNotFoundException exception=assertThrows(UserNotFoundException.class, () -> userService.getUserById(1l));
        Mockito.verify(userRepository,Mockito.times(1)).findByIdAndActive(Mockito.anyLong(),Mockito.any(ActiveStatus.class));
}
    @Test
    void updateUserPartially() {
        String email="test@company.com";
        String password="test";
        Mockito.when(userRepository.findByIdAndActive(1L,ActiveStatus.ACTIVE)).thenReturn(Optional.of(user));
        userService.updateUserPartially(1L,email,password);
        assertEquals(email,user.getEmail());
        assertEquals(password,user.getPassword());
        Mockito.verify(userRepository,Mockito.times(1)).save(Mockito.any(Users.class));
    }

    @Test
    void deleteUserById() {
        Mockito.when(userRepository.findByIdAndActive(1L, ActiveStatus.ACTIVE))
                .thenReturn(Optional.of(user));
        userService.deleteUserById(1L,ActiveStatus.DEACTIVE);
        assertEquals(ActiveStatus.DEACTIVE,user.getActive());
        Mockito.verify(userRepository,Mockito.times(1)).save(Mockito.any(Users.class));
    }

    @Test
    void updateUser() {
        UserRequest userRequest=new UserRequest();
        userRequest.setEmail("test@company.com");
        userRequest.setPassword("test");
        userRequest.setName("test");
        userRequest.setSurname("test");
        userRequest.setActive(ActiveStatus.ACTIVE.name());
        Mockito.when(userRepository.findByIdAndActive(1L,ActiveStatus.ACTIVE)).thenReturn(Optional.of(user));
        userService.updateUser(1l,userRequest);
        assertEquals(ActiveStatus.ACTIVE,user.getActive());
        assertEquals(userRequest.getName(),user.getName());
        assertEquals(userRequest.getSurname(),user.getSurname());
        assertEquals(userRequest.getEmail(),user.getEmail());
        assertEquals(userRequest.getPassword(),user.getPassword());
        Mockito.verify(userRepository,Mockito.times(1)).save(Mockito.any(Users.class));
    }

    }
