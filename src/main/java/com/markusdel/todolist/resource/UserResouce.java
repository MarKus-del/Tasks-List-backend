package com.markusdel.todolist.resource;

import com.markusdel.todolist.dto.UserCreateDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.UserNotFoundException;
import com.markusdel.todolist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserResouce {

    private UserService userService;

    public UserResouce(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateDTO newUser) {
        UserResponseDTO user = userService.createUser(newUser);
        URI uriUser = URI.create(String.format("/users/%s", user.getId()));
        return ResponseEntity.created(uriUser).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO>findUser(@PathVariable Long id) throws UserNotFoundException {
        UserResponseDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserCreateDTO user) throws UserNotFoundException {
        UserResponseDTO userResponseDTO = userService.updateUser(id, user);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
