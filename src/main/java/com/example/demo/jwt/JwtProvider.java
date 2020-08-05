package com.example.demo.jwt;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.utils.AppProperty;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

    private String jwtPassword;
    private int jwtExpiration;

    @Autowired
    public JwtProvider(AppProperty properties) {
        this.jwtPassword = properties.getSeedPassword();
        this.jwtExpiration = properties.getJwtExpiration();
    }

    public String generateJwtToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Date expiration = getDateExpiration(this.jwtExpiration);
        return Jwts.builder()
        .setSubject((userPrinciple.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(expiration)
        .claim("scopes", userPrinciple.getAuthorities())
        .signWith(SignatureAlgorithm.HS512, this.jwtPassword).compact();
    }
    
    public String generateJwtTokenGuest() {
        Date expiration = getDateExpirationGuest();
        return Jwts.builder()
        .setSubject(("Guest"))
        .setIssuedAt(new Date())
        .setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS512, this.jwtPassword).compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.jwtPassword).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
        	return false;
        }
    }

    public String getUsernameFromJWtToken(String token) {
        return Jwts.parser().setSigningKey(jwtPassword).parseClaimsJws(token).getBody().getSubject();
    }

    private static Date getDateExpiration(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }
    
    private static Date getDateExpirationGuest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 30);
        return calendar.getTime();
    }

}