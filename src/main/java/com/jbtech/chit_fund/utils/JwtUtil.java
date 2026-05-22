package com.jbtech.chit_fund.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // ── SIGNING KEY ───────────────────────────────────────
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ── GENERATE TOKEN ────────────────────────────────────
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)                          // ✅ was setSubject()
                .issuedAt(new Date())                       // ✅ was setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + expiration)) // ✅ was setExpiration()
                .signWith(getSigningKey())                  // ✅ no need to pass algorithm
                .compact();
    }

    // ── EXTRACT USERNAME ──────────────────────────────────
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ── EXTRACT EXPIRY ────────────────────────────────────
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    // ── VALIDATE TOKEN ────────────────────────────────────
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // ── IS EXPIRED ────────────────────────────────────────
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ── EXTRACT ALL CLAIMS ────────────────────────────────
    private Claims extractAllClaims(String token) {
        return Jwts.parser()                               // ✅ was parserBuilder()
                .verifyWith(getSigningKey())               // ✅ was setSigningKey()
                .build()
                .parseSignedClaims(token)                  // ✅ was parseClaimsJws()
                .getPayload();                             // ✅ was getBody()
    }
}