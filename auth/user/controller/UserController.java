package com.dudgns.backendauth.user.controller;

import com.dudgns.backendauth.dto.BaseRepsonseDto;
import com.dudgns.backendauth.exception.EmailAlreadyExistsException;
import com.dudgns.backendauth.exception.LoginFailedException;
import com.dudgns.backendauth.exception.NotVerifiedException;
import com.dudgns.backendauth.user.dto.RequestCreateUserDto;
import com.dudgns.backendauth.user.dto.RequestUserLoginDto;
import com.dudgns.backendauth.user.dto.ResponseCreateUserDto;
import com.dudgns.backendauth.user.dto.ResponseUserLoginDto;
import com.dudgns.backendauth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<BaseRepsonseDto> create(RequestCreateUserDto req) {

        ResponseCreateUserDto dto = null;

        try {
            dto = userService.create(req);
        } catch (EmailAlreadyExistsException eae) {
            return ResponseEntity.ok(BaseRepsonseDto.builder()
                    .statusCode(9997)
                    .status(eae.getMessage())
                    .data(null)
                    .build());
        } catch (NotVerifiedException nve) {
            return ResponseEntity.ok(BaseRepsonseDto.builder()
                    .statusCode(9998)
                    .status(nve.getMessage())
                    .data(null)
                    .build());
        }
        
        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .status("정상")
                        .data(dto)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<BaseRepsonseDto> loginUser(RequestUserLoginDto req) {
        ResponseUserLoginDto dto = null;

        try {
            dto = userService.loginUser(req);
        } catch (LoginFailedException lfe) {
            return ResponseEntity.ok(BaseRepsonseDto.builder()
                    .statusCode(9999)
                    .status(lfe.getMessage())
                    .data(null)
                    .build());
        }

        return ResponseEntity.ok(
                BaseRepsonseDto.builder()
                        .statusCode(200)
                        .status("정상")
                        .data(dto)
                        .build());
    }
}
