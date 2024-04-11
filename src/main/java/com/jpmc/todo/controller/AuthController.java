package com.jpmc.todo.controller;

import com.jpmc.todo.dto.AuthRequestDTO;
import com.jpmc.todo.dto.AuthResponseDTO;
import com.jpmc.todo.dto.UserDTO;
import com.jpmc.todo.exception.InvalidCredentialsException;
import com.jpmc.todo.exception.UserAlreadyExistsException;
import com.jpmc.todo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping(path = "/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO) throws UserAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userDTO));
    }

    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO authRequestDTO) throws InvalidCredentialsException {
        // implement login details
        AuthResponseDTO authResponseDTO = new AuthResponseDTO("Logged in successfully");
        return ResponseEntity.status(HttpStatus.OK).body(authResponseDTO);
    }
}
