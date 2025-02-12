package com.example.pwd_email_security_test.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender; // Spring에서 제공하는 메일 전송 객체

    // ✅ @Qualifier 사용: "naverMailSender"를 기본 발신자로 설정
    @Autowired
    public EmailService(@Qualifier("naverMailSender") JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            // 1. MimeMessage 객체 생성
            MimeMessage message = mailSender.createMimeMessage();

            // 2. MimeMessageHelper 설정 (true: multipart 사용)
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("care-point@naver.com"); // ✅ 발신자 (SMTP 인증 계정과 동일)
            helper.setTo(to); // ✅ 수신자 (to 매개변수 사용)
            helper.setSubject(subject); // ✅ 제목
            helper.setText(text, false); // ✅ 내용 (true: HTML 형식, false: 일반 텍스트)

            // 3. 메일 전송 실행
            mailSender.send(message);
            System.out.println("✅ 이메일 전송 성공!");

        } catch (Exception e) {
            System.err.println("❌ 이메일 전송 실패: " + e.getMessage());
        }
    }
}
