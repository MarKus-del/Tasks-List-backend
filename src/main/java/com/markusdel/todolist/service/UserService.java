package com.markusdel.todolist.service;

import com.markusdel.todolist.model.User;
import com.markusdel.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public User updateUser(Long id, User user) {
        User one = userRepository.getOne(id);
        User userUpdated = User.builder()
                .id(one.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .build();

        return userRepository.save(userUpdated);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
