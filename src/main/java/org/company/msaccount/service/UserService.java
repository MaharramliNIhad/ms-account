package org.company.msaccount.service;

import lombok.extern.slf4j.Slf4j;
import org.company.msaccount.dao.Users;
import org.company.msaccount.enums.ActiveStatus;
import org.company.msaccount.exception.UserNotFoundException;
import org.company.msaccount.mapper.UserMapper;
import org.company.msaccount.model.UserRequest;
import org.company.msaccount.model.UserResponse;
import org.company.msaccount.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public Users getUserById(Long userId) {
        return userRepository.findByIdAndActive(userId,ActiveStatus.ACTIVE).orElseThrow(()->new UserNotFoundException("Not found"));
}
    @Transactional
    public Long saveUser(UserRequest userRequest) {
        log.info("Actionlog.saveUser .start with " + userRequest);
        Long id = userRepository.save(UserMapper.INSTANCE.toUsers(userRequest)).getId();
        log.info("Actionlog.saveUser .start with id={}", id);
        return id;
    }
    public UserResponse findUserById(Long userId) {
        log.info("Actionlog.findUserById .start with userId={}", userId);
        UserResponse userResponse = UserMapper.INSTANCE.toUserResponse(getUserById(userId));
        log.info("Actionlog.findUserById .end with userId={}", userId);
return userResponse;
    }
    public void updateUserPartially(Long userId,String mail,String password) {
        log.info("Actionlog.updateUserPartially .start with userId={}", userId);
        Users users=userRepository.findById(userId).map(userss -> {
           Optional.ofNullable(mail).ifPresent(userss::setEmail);
           Optional.ofNullable(password).ifPresent(userss::setPassword);
       return userss;
       }).orElseThrow(()->new UserNotFoundException("Not found"));
        log.info("Actionlog.UpdateUserPartially .end with userId={}", userId);
    }
    public void deleteUserById(Long userId,ActiveStatus activeStatus) {
        log.info("Actionlog.deleteUserById .start with userId={}", userId);
        Users user = getUserById(userId);
        user.setActive(activeStatus);
        userRepository.save(user);
        log.info("Actionlog.deleteUserById .end with userId={}", userId);
    }
    public void updateUser(Long id,UserRequest userRequest) {
        log.info("Actionlog.updateUser .start with id={}", id);
        Users userById = getUserById(id);
        userById.setEmail(userRequest.getEmail());
        userById.setPassword(userRequest.getPassword());
        userById.setName(userRequest.getName());
        userById.setSurname(userRequest.getSurname());
        userRepository.save(userById);
        log.info("Actionlog.updateUser .end with id={}", id);

    }
}
