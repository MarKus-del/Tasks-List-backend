package com.markusdel.todolist.service;

import com.markusdel.todolist.dto.UserCreateDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.ExistingEmailException;
import com.markusdel.todolist.exception.UserNotFoundException;
import com.markusdel.todolist.mapper.UserMapper;
import com.markusdel.todolist.model.User;
import com.markusdel.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User returnUser(Long id) throws UserNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }
    
    public User returnUserByEmail(String email) throws ExistingEmailException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ExistingEmailException(email));
        return user;
    }

    public UserResponseDTO createUser(UserCreateDTO newUser) {
        User user = userMapper.toEntity(newUser);
        UserResponseDTO userSaved = userMapper.toUserResponseDTO(userRepository.save(user));
        return userSaved;
    }

    public UserResponseDTO getUser(Long id) throws UserNotFoundException {
        User user = this.returnUser(id);
        return userMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO updateUser(Long id, @Valid UserCreateDTO user) throws UserNotFoundException {
        User byId = this.returnUser(id);
        User userUpdated = User.builder()
                .id(byId.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .build();

        User savedUser = userRepository.save(userUpdated);
        return userMapper.toUserResponseDTO(savedUser);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        this.returnUser(id);
        userRepository.deleteById(id);
    }
}
