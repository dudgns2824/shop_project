package com.dudgns.auth.common;

import com.dudgns.auth.common.object.SendEmailObjectDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmailSendService {

    private final JavaMailSender javaMailSender;

    public Boolean send(SendEmailObjectDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(dto.getFrom());
        message.setTo(dto.getTo());
        message.setSubject("인증 메일 입니다.");
        message.setText(dto.getCode());

        javaMailSender.send(message);
        return true;
    }
}
