package com.markusdel.todolist.service;

import com.markusdel.todolist.Utils.Utils;
import com.markusdel.todolist.dto.UserCreateDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.EmailNotFoundException;
import com.markusdel.todolist.exception.ExistingEmailException;
import com.markusdel.todolist.exception.InvalidEmailException;
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
    private Utils utils;

    public UserService(UserRepository userRepository, UserMapper userMapper, Utils utils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.utils = utils;
    }

    public User returnUser(Long id) throws UserNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }
    
    public User returnUserByEmail(String email) throws EmailNotFoundException, InvalidEmailException {
        utils.validateEmail(email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
        return user;
    }

    public UserResponseDTO createUser(UserCreateDTO newUser) throws ExistingEmailException, InvalidEmailException {
        User user = userMapper.toEntity(newUser);
        utils.validateEmail(user.getEmail());
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());

        if(byEmail.isPresent()) {
            throw new ExistingEmailException(newUser.getEmail());
        }


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
