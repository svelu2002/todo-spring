package com.jpmc.todo.service;

import com.jpmc.todo.dto.UserProfileDTO;
import com.jpmc.todo.exception.UserNotFoundException;
import com.jpmc.todo.model.UserProfileEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserProfileDTO updateUserProfile(int userId, UserProfileDTO userProfileDTO) throws UserNotFoundException;

    public List<UserProfileDTO> getUsersByGender(String gender);
}
