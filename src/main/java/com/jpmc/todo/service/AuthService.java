package com.jpmc.todo.service;

import com.jpmc.todo.dto.AuthRequestDTO;
import com.jpmc.todo.dto.UserDTO;
import com.jpmc.todo.exception.InvalidCredentialsException;
import com.jpmc.todo.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public UserDTO register(UserDTO userDTO) throws UserAlreadyExistsException;

    public void login(AuthRequestDTO loginDTO) throws InvalidCredentialsException;
}
