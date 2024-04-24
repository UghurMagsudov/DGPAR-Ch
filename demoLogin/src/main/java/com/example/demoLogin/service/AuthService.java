package com.example.demoLogin.service;

import com.example.demoLogin.dto.LoginDto;
import com.example.demoLogin.dto.RegisterDto;
import com.example.demoLogin.dto.UserDto;
import com.example.demoLogin.exceptions.IncorrectUsernameOrPasswordException;
import com.example.demoLogin.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UserService userService;


    private final JwtTokenProvider jwtTokenProvider;


    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }


    @Transactional
    public String registerNewUser(RegisterDto registerDto) {

        UserDto userDto = userService.registerNewUser(registerDto);
        return jwtTokenProvider.createToken(userDto.getUsername());
    }


    public String authenticateUser(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            return jwtTokenProvider.createToken(authentication.getName());
        } catch (AuthenticationException ex) {
            throw new IncorrectUsernameOrPasswordException("Username or password is incorrect.");
        }
    }
}
