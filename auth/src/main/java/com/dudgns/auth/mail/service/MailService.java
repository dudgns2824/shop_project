package com.dudgns.auth.mail.service;

import com.dudgns.auth.common.EmailSendService;
import com.dudgns.auth.common.object.SendEmailObjectDto;
import com.dudgns.auth.dto.RequestMailDto;
import com.dudgns.auth.entity.redis.MailRequestEntity;
import com.dudgns.auth.enums.VerifyType;
import com.dudgns.auth.exception.EmailVerifyFailedException;
import com.dudgns.auth.exception.EmailVerifyMaxRequestException;
import com.dudgns.auth.exception.EmailVerifyMaxRequestPerTimeException;
import com.dudgns.auth.mail.dto.RequestMailVerifyDto;
import com.dudgns.auth.mail.dto.RequestVerifyDto;
import com.dudgns.auth.mail.dto.ResponseMailVerifyDto;
import com.dudgns.auth.mail.dto.ResponseVerifyDto;
import com.dudgns.auth.repository.redis.MailRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class MailService {

    @Value("${spring.mail.max-request-count}")
    private Integer maxRequestCount;

    @Value("${spring.mail.max-request-minutes}")
    private Integer maxRequestMinutes;

    @Value("${spring.mail.max-request-per-time}")
    private Integer maxRequestPerTime;

    @Value("${spring.mail.mail-verify-expire-time}")
    private Integer mailVerifyExpireTime;

    @Value("${spring.mail.mail-request-expire}")
    private Long mailRequestExpire;

    @Value("${spring.mail.from-email}")
    private String fromEmail;

    private final EmailSendService emailSendService;

    private final MailRequestRepository mailRequestRepository;

    public ResponseMailVerifyDto verify(RequestMailVerifyDto req) {

        Optional<MailRequestEntity> mailRequestEntityOptional = mailRequestRepository.findById(req.getEmail());

        boolean isVerified = false;

        LocalDateTime now = LocalDateTime.now();

        if (mailRequestEntityOptional.isPresent()) {
            MailRequestEntity mailRequestEntity = mailRequestEntityOptional.get();

            for (RequestMailDto requestMailDto : mailRequestEntity.getRequests()) {
                if (now.minus(mailVerifyExpireTime, ChronoUnit.MINUTES).isBefore(requestMailDto.getRequestTime()) && !requestMailDto.isVerified() && requestMailDto.getCode().equals(String.format("%06d", req.getCode()))) {
                    requestMailDto.setVerified(true);
                    isVerified = true;
                }
            }

            mailRequestRepository.save(mailRequestEntity);
        } else {
            throw new EmailVerifyFailedException();
        }

        return ResponseMailVerifyDto.builder()
                .success(isVerified)
                .email(req.getEmail())
                .build();

    }

    public ResponseVerifyDto request(RequestVerifyDto req) {
        Optional<MailRequestEntity> mailRequestEntityOptional = mailRequestRepository.findById(req.getEmail());

        String code = String.format("%06d", Long.parseLong(RandomStringUtils.randomNumeric(6, 6), 10));

        Boolean isSuccess = false;

        LocalDateTime now = LocalDateTime.now();

        if (mailRequestEntityOptional.isPresent()) {
            MailRequestEntity mailRequestEntity = mailRequestEntityOptional.get();

            List<RequestMailDto> requestMailDtoList = mailRequestEntity.getRequests();

            int cnt = 1;

            if (requestMailDtoList.size() >= maxRequestCount) {
                throw new EmailVerifyMaxRequestException();
            }

            for (RequestMailDto requestMailDto : requestMailDtoList) {
                if (now.minus(maxRequestMinutes, ChronoUnit.MINUTES).isBefore(requestMailDto.getRequestTime())) cnt++;

                if (cnt > maxRequestPerTime) {
                    throw new EmailVerifyMaxRequestPerTimeException();
                }
            }

            mailRequestEntity.getRequests().add(RequestMailDto.builder()
                    .confirm(false)
                    .type(VerifyType.REGISTER)
                    .code(code)
                    .requestTime(now)
                    .build());

            mailRequestRepository.save(mailRequestEntity);
        } else {
            List<RequestMailDto> requestMailDtoList = new ArrayList<>();

            requestMailDtoList.add(RequestMailDto.builder()
                    .code(code)
                    .verified(false)
                    .confirm(false)
                    .requestTime(now)
                    .build());

            mailRequestRepository.save(MailRequestEntity.builder()
                    .email(req.getEmail())
                    .verified(false)
                    .expire(mailRequestExpire)
                    .requests(requestMailDtoList)
                    .build());
        }

        emailSendService.send(SendEmailObjectDto.builder()
                .from(fromEmail)
                .to(req.getEmail())
                .build());

        return ResponseVerifyDto
                .builder()
                .email(req.getEmail())
                .code(code)
                .build();
    }

}
