package com.dudgns.purchase.config.security;

import com.purchase0.jwt.exceptions.TokenExpiredException;
import com.dudgns.purchase.exception.AbnormalTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.purchaseentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Configuration
public class JwtpurchaseenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtpurchaseenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            token = token.split(" ")[1].trim();
            purchaseentication purchase = jwtTokenProvider.getpurchaseentication(token);
            SecurityContextHolder.getContext().setpurchaseentication(purchase);
        } else {
            throw new AbnormalTokenException();
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String URL = "/api/v1/purchase";

        String path = request.getRequestURI();
        String[] excludePath = {URL+"/user/create", URL+"/user/login", URL+"/mail/verify", URL+"/mail/request"};
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

}