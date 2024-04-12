package com.jpmc.todo.service;

import com.jpmc.todo.dto.AuthRequestDTO;
import com.jpmc.todo.dto.UserDTO;
import com.jpmc.todo.exception.InvalidCredentialsException;
import com.jpmc.todo.exception.UserAlreadyExistsException;
import com.jpmc.todo.model.UserEntity;
import com.jpmc.todo.model.UserProfileEntity;
import com.jpmc.todo.repository.UserProfileRepository;
import com.jpmc.todo.repository.UserRepository;
import com.jpmc.todo.util.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDTO register(UserDTO userDTO) throws UserAlreadyExistsException {
        UserEntity userEntity = userRepository.findByUsername(userDTO.username());
        if (userEntity != null) {
            throw new UserAlreadyExistsException("User with the name " + userDTO.username() + " already exists.");
        } else {
            UserEntity newUserEntity = userRepository.save((UserEntity) EntityDTOConverter.convertToEntity(userDTO));
            UserProfileEntity userProfileEntity = new UserProfileEntity(0, userDTO.fullName(), null, null, newUserEntity);
            userProfileEntity = userProfileRepository.save(userProfileEntity);
            newUserEntity.setProfile(userProfileEntity);
            newUserEntity = userRepository.save(newUserEntity);
            return (UserDTO) EntityDTOConverter.convertToDTO(newUserEntity);
        }
    }

    @Override
    public void login(AuthRequestDTO loginDTO) throws InvalidCredentialsException {
        UserEntity user = userRepository.findByUsername(loginDTO.username());
        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }
    }
}
