package com.dudgns.backendauth.mail.controller;

import com.dudgns.backendauth.dto.BaseRepsonseDto;
import com.dudgns.backendauth.mail.dto.RequestMailVerifyDto;
import com.dudgns.backendauth.mail.dto.RequestVerifyDto;
import com.dudgns.backendauth.mail.dto.ResponseMailVerifyDto;
import com.dudgns.backendauth.mail.dto.ResponseVerifyDto;
import com.dudgns.backendauth.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/verify")
    public ResponseEntity<BaseRepsonseDto> verify(RequestVerifyDto req) {

        ResponseVerifyDto dto = mailService.requestVerify(req);

        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .data(dto)
                        .status("정상")
                        .build()
        );
    }

    @PostMapping("/verify-mail")
    public ResponseEntity<BaseRepsonseDto> verifyMail(RequestMailVerifyDto req) {
        ResponseMailVerifyDto dto = mailService.verifyMail(req);

        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .data(dto)
                        .status("정상")
                        .build()
        );
    }

}
