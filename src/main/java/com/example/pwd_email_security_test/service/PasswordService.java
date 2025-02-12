package com.example.pwd_email_security_test.service;

import com.example.pwd_email_security_test.mapper.UserMapper;
import com.example.pwd_email_security_test.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean sendTemporaryPassword(String email) {
        if (userMapper.checkEmailExists(email) == 0) {
            return false;
        }

        String tempPassword = PasswordUtil.generateTempPassword(8);
        userMapper.updateTempPassword(email, passwordEncoder.encode(tempPassword));

        String subject = "임시 비밀번호 안내";
        String text = "임시 비밀번호: " + tempPassword + "<br>10분 이내에 사용해주세요.";

        //emailService.sendEmail(email, subject, text);
        return true;
    }

    public boolean verifyTempPassword(String email, String tempPassword) {
        String storedPassword = userMapper.getTempPassword(email);
        return storedPassword != null && passwordEncoder.matches(tempPassword, storedPassword);
    }

    public boolean changePassword(String email, String tempPassword, String newPassword) {
        if (!verifyTempPassword(email, tempPassword)) {
            return false;
        }
        userMapper.updatePassword(email, passwordEncoder.encode(newPassword));
        return true;
    }

}
