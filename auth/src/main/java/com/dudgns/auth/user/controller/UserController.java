package com.dudgns.auth.user.controller;

import com.dudgns.auth.dto.BaseRepsonseDto;
import com.dudgns.auth.exception.EmailAlreadyExistsException;
import com.dudgns.auth.exception.LoginFailedException;
import com.dudgns.auth.exception.NotVerifiedException;
import com.dudgns.auth.user.dto.RequestCreateUserDto;
import com.dudgns.auth.user.dto.RequestUserLoginDto;
import com.dudgns.auth.user.dto.ResponseCreateUserDto;
import com.dudgns.auth.user.dto.ResponseUserLoginDto;
import com.dudgns.auth.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<BaseRepsonseDto> create(@RequestBody @Valid RequestCreateUserDto req) {

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

    @GetMapping("/login")
    public ResponseEntity<BaseRepsonseDto> loginUser(@RequestBody @Valid RequestUserLoginDto req) {
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
