package com.ra.SpringBootJWT.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.ra.SpringBootJWT.security.CustomUserDetails;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${ra.jwt.secret}")
    private String JWT_SECRET;
    @Value("${ra.jwt.expiration}")
    private int JWT_EXPIRATION;
    //Tao jwt tu thong tin cua user
    public String generateToken(CustomUserDetails customUserDetails){
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + JWT_EXPIRATION);
        //Tao chuoi jwt tu userName
        return Jwts.builder().setSubject(customUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    //Lay thong tin user tu jwt
    public String getUserNameFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET)
                .parseClaimsJws(token).getBody();
        //tra lai userName
        return claims.getSubject();
    }

    //validate token
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token).getBody();
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid JWT Token");
        }catch (ExpiredJwtException e){
            log.error("Expired JWT Token");
        }catch (UnsupportedJwtException e){
            log.error("Unsupported JWT Token");
        }catch (IllegalArgumentException e){
            log.error("JWT claims String is empty");
        }
        return false;
    }
}

