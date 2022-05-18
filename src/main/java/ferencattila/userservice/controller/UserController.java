package ferencattila.userservice.controller;

import ferencattila.userservice.dto.SaveUserCommand;
import ferencattila.userservice.dto.UserDto;
import ferencattila.userservice.service.UserService;
import ferencattila.userservice.valueobject.Department;
import ferencattila.userservice.valueobject.ResponseTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    @CircuitBreaker(name = "User-Service", fallbackMethod = "serviceUserFallback")
    public ResponseTemplate getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment method of UserController");
        return service.getUserWithDepartment(userId);
    }

    public ResponseTemplate serviceUserFallback(Exception iae) {
        ResponseTemplate template = new ResponseTemplate();
        template.setUser(new UserDto(0L, "", "", "", 0L));
        template.setDepartment(new Department(0L, iae.getMessage(), "", ""));
        return template;
    }
}
