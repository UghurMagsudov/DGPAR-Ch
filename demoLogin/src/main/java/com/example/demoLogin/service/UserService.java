package com.example.demoLogin.service;

import com.example.demoLogin.dto.RegisterDto;
import com.example.demoLogin.dto.UserDto;
import com.example.demoLogin.exceptions.UsernameAlreadyTakenException;
import com.example.demoLogin.model.User;
import com.example.demoLogin.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto registerNewUser(RegisterDto registerDto) {
        if (existsByUsername(registerDto.getUsername())) {
            throw new UsernameAlreadyTakenException("Username already taken.");
        }

        User newUser = new User();
        newUser.setUsername(registerDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        newUser = userRepository.save(newUser);

        return convertToUserDto(newUser);
    }

    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());  // Consider excluding password or using a separate DTO
        return userDto;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
