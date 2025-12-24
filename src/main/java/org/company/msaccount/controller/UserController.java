package org.company.msaccount.controller;


import org.company.msaccount.enums.ActiveStatus;
import org.company.msaccount.model.CardResponse;
import org.company.msaccount.model.UserRequest;
import org.company.msaccount.model.UserResponse;
import org.company.msaccount.service.CardEventService;
import org.company.msaccount.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final CardEventService cardEventService;
    public UserController(UserService userService, CardEventService cardEventService) {
        this.userService = userService;
        this.cardEventService = cardEventService;
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
    @GetMapping("/card/{id}")
    public CardResponse getCard(@PathVariable("id") Long id) {
        return userService.getCardById(id);
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserPartially(@PathVariable Long id,@RequestParam(required = false) String email
            ,@RequestParam(required = false) String password) {
        userService.updateUserPartially(id, email, password);
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id, ActiveStatus.DEACTIVE);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }
    @PostMapping("card")
    @ResponseStatus(HttpStatus.CREATED)
    public void cardsSave(@RequestBody UserRequest userRequest) {
         cardEventService.CreateCardCreatedEvent(userRequest);
    }
    @GetMapping("cards/{id}")
    public CardResponse getCards(@PathVariable("id") Long id) {
        return userService.getCardById(id);
    }
}
