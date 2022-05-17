package ferencattila.userservice.mapper;

import ferencattila.userservice.dto.SaveUserCommand;
import ferencattila.userservice.dto.UserDto;
import ferencattila.userservice.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto dtoFromUser(User user);

    List<UserDto> dtoFromUser(List<User> users);

    User userFromSaveUserCommand(SaveUserCommand command);
}
