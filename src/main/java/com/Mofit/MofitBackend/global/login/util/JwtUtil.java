package com.Mofit.MofitBackend.global.login.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret-key}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // ✅ Use Keys.hmacShaKeyFor()
    }

    public String getLoginId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey) // ✅ Use parserBuilder().setSigningKey()
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("loginId", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public String createJwt(String loginId, String role, Long expiredMs) {
        return Jwts.builder()
                .claim("loginId", loginId)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis())) // ✅ Use setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // ✅ Use setExpiration()
                .signWith(secretKey) // ✅ Explicitly set SignatureAlgorithm
                .compact();
    }

    public String createTempJwt(String loginId, Long expiredMs){
        return Jwts.builder()
                .claim("loginId", loginId)
                .issuedAt(new Date(System.currentTimeMillis())) // ✅ Use setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // ✅ Use setExpiration()
                .signWith(secretKey) // ✅ Explicitly set SignatureAlgorithm
                .compact();
    }
}
