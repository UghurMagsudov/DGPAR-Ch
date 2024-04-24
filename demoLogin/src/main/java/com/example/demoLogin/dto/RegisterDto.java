package com.example.demoLogin.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    @NotNull
    @Size(min = 6, max = 20)
    private String username;
    @NotNull
    @Size(min = 8, max = 20)
    private String password;
}
