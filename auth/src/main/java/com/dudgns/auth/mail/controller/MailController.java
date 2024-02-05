package com.dudgns.auth.mail.controller;

import com.dudgns.auth.dto.BaseRepsonseDto;
import com.dudgns.auth.mail.dto.ResponseMailVerifyDto;
import com.dudgns.auth.mail.dto.ResponseVerifyDto;
import com.dudgns.auth.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/mail")
public class MailController {

    private final MailService mailService;

    @GetMapping("/request")
    public ResponseEntity<BaseRepsonseDto> request(@RequestParam(value = "email") String email) {

        ResponseVerifyDto dto = mailService.request(email);

        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .data(dto)
                        .status("정상")
                        .build()
        );
    }

    @GetMapping("/verify")
    public ResponseEntity<BaseRepsonseDto> verifyMail(@RequestParam(value = "email") String email,
                                                      @RequestParam(value = "code") String code) {
        ResponseMailVerifyDto dto = mailService.verify(email, code);

        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .data(dto)
                        .status("정상")
                        .build()
        );
    }

}
