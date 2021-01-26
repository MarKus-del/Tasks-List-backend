package com.markusdel.todolist.resource;

import com.markusdel.todolist.dto.AuthenticateResponseDTO;
import com.markusdel.todolist.dto.LoginDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.EmailNotFoundException;
import com.markusdel.todolist.exception.UserNotFoundException;
import com.markusdel.todolist.service.TokenService;
import com.markusdel.todolist.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/login")
public class AuthenticateResource {

    private AuthenticationManager authManager;
    private TokenService tokenService;
    private UserService userService;

    public AuthenticateResource(AuthenticationManager authManager, TokenService tokenService, UserService userService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AuthenticateResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) throws EmailNotFoundException {
        UserResponseDTO user = userService.login(loginDTO);

        UsernamePasswordAuthenticationToken dadosLogin = loginDTO.converter();
        Authentication authenticate = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarToken(authenticate);
        return ResponseEntity.ok().body(new AuthenticateResponseDTO("Bearer", token));
    }
}
