package org.company.msaccount.controller;

import org.apache.catalina.User;
import org.company.msaccount.dao.Users;
import org.company.msaccount.enums.ActiveStatus;
import org.company.msaccount.model.UserRequest;
import org.company.msaccount.model.UserResponse;
import org.company.msaccount.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long usersSave(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }
    @GetMapping("{id}")
    public UserResponse getUser(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserPartially(@PathVariable Long id,@RequestParam(required = false) String email
            ,@RequestParam(required = false) String password) {
        userService.updateUserPartially(id, email, password);
    }
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id, ActiveStatus.DEACTIVE);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }
}
