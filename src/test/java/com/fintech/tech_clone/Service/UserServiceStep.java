package com.fintech.tech_clone.Service;

import com.fintech.tech_clone.dto.request.user.UserCreationRequest;
import com.fintech.tech_clone.entity.User;
import com.fintech.tech_clone.service.UserService;

import net.serenitybdd.annotations.Step;
import org.junit.jupiter.api.Assertions;

public class UserServiceStep {
    @Step("Tạo người dùng với dữ liệu hợp lệ")
    public User createUser(UserService userService, UserCreationRequest request) {
        return userService.createUser(request);
    }

    @Step("Kiểm tra lỗi {0}")
    public void verifyException(RuntimeException exception, String expectedMessage) {
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}
