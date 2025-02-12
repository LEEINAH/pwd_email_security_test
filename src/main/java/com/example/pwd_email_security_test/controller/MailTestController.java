package com.example.pwd_email_security_test.controller;

import com.example.pwd_email_security_test.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailTestController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/mail/send")
    public String sendMail(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text) {
        // to 값을 users 테이블에 담겨 있는 해당 회원의 이메일 값을 뽑아와서 넣어야함.
        emailService.sendEmail(to, subject, text);
        return "✅ 이메일 전송 요청 완료!";
    }
}
