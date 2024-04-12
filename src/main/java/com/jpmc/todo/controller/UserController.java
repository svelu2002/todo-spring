package com.jpmc.todo.controller;

import com.jpmc.todo.dto.UserProfileDTO;
import com.jpmc.todo.exception.UserNotFoundException;
import com.jpmc.todo.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(path = "/{userId}/profile")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable int userId, @RequestBody UserProfileDTO userProfileDTO) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserProfile(userId, userProfileDTO));
    }

    @GetMapping(path = "/by-gender/{gender}")
    public ResponseEntity<List<UserProfileDTO>> getUsersByGender(@PathVariable String gender) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersByGender(gender));
    }
}
