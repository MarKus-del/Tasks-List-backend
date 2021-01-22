package com.markusdel.todolist.resource;

import com.markusdel.todolist.model.User;
import com.markusdel.todolist.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResouce {

    private UserService userService;

    public UserResouce(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
