package com.markusdel.todolist.mapper;

import com.markusdel.todolist.dto.UserCreateDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public User toEntity (UserCreateDTO userCreateDTO) {
        return  MODEL_MAPPER.map(userCreateDTO, User.class);
    }

    public User toEntity (UserResponseDTO userResponseDTO) {
        return  MODEL_MAPPER.map(userResponseDTO, User.class);
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        return MODEL_MAPPER.map(user, UserResponseDTO.class);
    }
}
