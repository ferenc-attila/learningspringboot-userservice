package ferencattila.userservice;

import ferencattila.userservice.dto.SaveUserCommand;
import ferencattila.userservice.dto.UserDto;
import ferencattila.userservice.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto dtoFromUser(User user);

    List<UserDto> dtoFromUser(List<User> users);

    User userFromSaveUserCommand(SaveUserCommand command);
}
