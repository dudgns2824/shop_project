package com.dudgns.backendauth.common;

import com.dudgns.backendauth.common.object.SendEmailObjectDto;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmailSendService {

    private JavaMailSender javaMailSender;

    public Boolean send(SendEmailObjectDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(dto.getFrom());
        message.setTo(dto.getTo());
        message.setSubject("인증 메일 입니다.");
        message.setText(String.format("%04d", Long.parseLong(RandomStringUtils.randomNumeric(4, 4), 10)));
        javaMailSender.send(message);
        return true;
    }
}
