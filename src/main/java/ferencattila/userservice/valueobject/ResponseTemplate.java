package ferencattila.userservice.valueobject;

import ferencattila.userservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate {

    private UserDto user;

    private Department department;
}
