package com.example.pwd_email_security_test.controller;

import com.example.pwd_email_security_test.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/find-password")
    public String findPassword(@RequestParam String email) {
        if (passwordService.sendTemporaryPassword(email)) {
            return "임시 비밀번호가 이메일로 발송되었습니다.";
        }
        return "등록되지 않은 이메일입니다.";
    }

    @PostMapping("/verify-temp")
    public String verifyTempPassword(@RequestParam String email, @RequestParam String tempPassword) {
        if (passwordService.verifyTempPassword(email, tempPassword)) {
            return "임시 비밀번호가 일치합니다.";
        }
        return "임시 비밀번호가 틀렸습니다.";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String email, @RequestParam String tempPassword, @RequestParam String newPassword) {
        if (passwordService.changePassword(email, tempPassword, newPassword)) {
            return "비밀번호가 변경되었습니다.";
        }
        return "임시 비밀번호가 틀렸거나 만료되었습니다.";
    }
}
