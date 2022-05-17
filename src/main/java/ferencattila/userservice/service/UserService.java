package ferencattila.userservice.service;

import ferencattila.userservice.mapper.UserMapper;
import ferencattila.userservice.dto.SaveUserCommand;
import ferencattila.userservice.dto.UserDto;
import ferencattila.userservice.entity.User;
import ferencattila.userservice.repository.UserRepository;
import ferencattila.userservice.valueobject.Department;
import ferencattila.userservice.valueobject.ResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private UserRepository repository;

    private UserMapper mapper;

    private RestTemplate restTemplate;

    public UserService(UserRepository repository, UserMapper mapper, RestTemplate restTemplate) {
        this.repository = repository;
        this.mapper = mapper;
        this.restTemplate = restTemplate;
    }

    public UserDto saveUser(SaveUserCommand command) {
        log.info("Inside saveUser of UserService");
        User result = repository.save(mapper.userFromSaveUserCommand(command));
        return mapper.dtoFromUser(result);
    }

    public ResponseTemplate getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment method of UserService");
        ResponseTemplate template = new ResponseTemplate();
        Optional<User> result = repository.findById(userId);
        User user = result.orElseThrow(
                () -> new IllegalArgumentException("Cannot find user by id " + userId));
        Department department = restTemplate.getForObject("http://localhost:8080/api/departments/" + user.getDepartmentId(),
                Department.class);
        template.setUser(mapper.dtoFromUser(user));
        template.setDepartment(department);
        return template;
    }
}
