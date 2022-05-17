package ferencattila.userservice.controller;

import ferencattila.userservice.dto.SaveUserCommand;
import ferencattila.userservice.dto.UserDto;
import ferencattila.userservice.service.UserService;
import ferencattila.userservice.valueobject.ResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/")
    public UserDto saveUser(@RequestBody SaveUserCommand command) {
        log.info("Inside saveUser method of UserController");
        return service.saveUser(command);
    }

    @GetMapping("{id}")
    public ResponseTemplate getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment method of UserController");
        return service.getUserWithDepartment(userId);
    }
}
