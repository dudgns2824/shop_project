package com.dudgns.auth.mail.controller;

import com.dudgns.auth.dto.BaseRepsonseDto;
import com.dudgns.auth.mail.dto.RequestMailVerifyDto;
import com.dudgns.auth.mail.dto.RequestVerifyDto;
import com.dudgns.auth.mail.dto.ResponseMailVerifyDto;
import com.dudgns.auth.mail.dto.ResponseVerifyDto;
import com.dudgns.auth.mail.service.MailService;
import jakarta.validation.Valid;
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
    public ResponseEntity<BaseRepsonseDto> request(@RequestBody @Valid RequestVerifyDto req) {

        ResponseVerifyDto dto = mailService.request(req);

        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .data(dto)
                        .status("정상")
                        .build()
        );
    }

    @GetMapping("/verify")
    public ResponseEntity<BaseRepsonseDto> verifyMail(@RequestBody @Valid RequestMailVerifyDto req) {
        ResponseMailVerifyDto dto = mailService.verify(req);

        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .data(dto)
                        .status("정상")
                        .build()
        );
    }

}
