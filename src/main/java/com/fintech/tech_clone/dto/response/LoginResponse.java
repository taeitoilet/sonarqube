package com.fintech.tech_clone.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse {
    String token;
    private boolean validate;
}
