package com.demo.todoservice.security.jwt;

import com.demo.todoservice.exception.JWTUserIdNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.UUID;

@Slf4j
@Component
public class JWTUtils {

    @Value("${todo-service.jwt.secret}")
    private String jwtSecret;

    public UUID getUserIdFromJwtToken(String headerAuth) {
        log.debug("getUserIdFromJwtToken() called --->");
        String authToken = parseJwt(headerAuth);
        if (authToken != null && validateJwtToken(authToken)) {
            String userId = Jwts.parserBuilder().setSigningKey(key()).build()
                    .parseClaimsJws(authToken).getBody().getSubject();
            if (userId.isEmpty()) throw new JWTUserIdNotFoundException();
            return UUID.fromString(userId);
        }
        return null;
    }

    private String parseJwt(String headerAuth) {
        log.debug("parseJwt() called --->");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    private boolean validateJwtToken(String authToken) {
        log.debug("validateJwtToken() called --->");
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    private Key key() {
        log.debug("key() called --->");
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
