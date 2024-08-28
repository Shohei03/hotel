package com.example.Hotel.domain.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailSender mailSender;

    public void sendPasswordResetEmail(String email) {
        UserEntity user = userMapper.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            userMapper.updateToken(user);

            String resetUrl = "http://localhost:8080/reset-password?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the link below:\n" + resetUrl);

            mailSender.send(message);
        }

    }

    public boolean resetPassword(String token, String newPassword) {
        UserEntity user = userMapper.findByResetToken(token);
        if (user != null) {
            //user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            user.setPassword(newPassword);
            user.setResetToken(null);
            userMapper.updatePassword(user);
            return true;
        }
        return false;
    }
}
