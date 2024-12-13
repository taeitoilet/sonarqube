package com.fintech.tech_clone.dto.request.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String user_name;
    private String user_password;
}
