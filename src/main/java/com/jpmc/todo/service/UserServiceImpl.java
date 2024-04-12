package com.jpmc.todo.service;

import com.jpmc.todo.dto.UserProfileDTO;
import com.jpmc.todo.exception.UserNotFoundException;
import com.jpmc.todo.model.UserEntity;
import com.jpmc.todo.model.UserProfileEntity;
import com.jpmc.todo.repository.UserProfileRepository;
import com.jpmc.todo.repository.UserRepository;
import com.jpmc.todo.util.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfileDTO updateUserProfile(int userId, UserProfileDTO userProfileDTO) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with the id " + userId + " not found"));
        UserProfileEntity userProfileEntity = userEntity.getProfile();
        userProfileEntity.setDob(userProfileDTO.dob());
        userProfileEntity.setGender(userProfileDTO.gender());
        userProfileEntity.setFullName(userProfileDTO.fullName());
        UserProfileEntity savedUserProfileEntity = userProfileRepository.save(userProfileEntity);
//        userEntity = userRepository.save(userEntity);
        return (UserProfileDTO) EntityDTOConverter.convertToDTO(savedUserProfileEntity);
    }

    @Override
    public List<UserProfileDTO> getUsersByGender(String gender) {
        return userProfileRepository.getUsersByGender(gender).stream().map(userProfileEntity -> (UserProfileDTO) EntityDTOConverter.convertToDTO(userProfileEntity)).toList();
    }
}
