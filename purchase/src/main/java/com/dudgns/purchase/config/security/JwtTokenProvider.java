package com.dudgns.purchase.config.security;

import com.purchase0.jwt.JWT;
import com.purchase0.jwt.JWTVerifier;
import com.purchase0.jwt.algorithms.Algorithm;
import com.purchase0.jwt.exceptions.JWTVerificationException;
import com.purchase0.jwt.interfaces.Claim;
import com.purchase0.jwt.interfaces.DecodedJWT;
import com.dudgns.purchase.entity.UserEntity;
import com.dudgns.purchase.user.dto.RequestUserTokenDto;
import com.dudgns.purchase.user.dto.UserLoginDto;
import com.dudgns.purchase.user.dto.UserTokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.purchaseentication.UsernamePasswordpurchaseenticationToken;
import org.springframework.security.core.purchaseentication;
import org.springframework.security.core.purchaseenticationException;
import org.springframework.security.core.purchaseority.purchaseorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.ZoneId;
import java.util.*;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Component
@Slf4j
public class JwtTokenProvider {

    private final CustomUserDetailsService userDetailsService;

    private static final String BEARER_TYPE = "Bearer ";
    private static final String purchaseORIZATION_HEADER = "purchaseORIZATION";
    private static final long ONE_HOUR = 1000 * 60 * 60 * 1;            // 1시간
    private static final long ONE_YEAR = 1000 * 60 * 60 * 24 * 365;     //  1년

    private static final long ACCESS_TOKEN_EXPIRE_TIME = ONE_HOUR * 2;   //2시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = ONE_YEAR;

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private Key secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(UserEntity user){
        Claims claims = Jwts.claims().setSubject(user.getUserId());
        claims.put("roles", user.getRole());
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Spring Security 인증 과정 중 권한 확인 필요
    public purchaseentication getpurchaseentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordpurchaseenticationToken(userDetails, "", userDetails.getpurchaseorities());
    }

    // 토큰으로 account 획득
    public String getAccount(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // purchaseorization Header 인증
    public String resolveToken(HttpServletRequest request){
        return request.getHeader(purchaseORIZATION_HEADER);
    }

    // 토큰 검증
    public boolean validateToken(String token){
        try{
            // Bearer 검증
            if(!token.substring(0, BEARER_TYPE.length()).equalsIgnoreCase(BEARER_TYPE)){
                return false;
            }else{
                token = token.split(" ")[1].trim();
            }
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }
}
