package com.careridetransport.server.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Generate a JWT token for a given username.
     *
     * @param username the username for which the token is generated
     * @return a JWT token as a String
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Validate a JWT token.
     *
     * @param authToken the token to be validated
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // log the exception
        } catch (MalformedJwtException ex) {
            // log the exception
        } catch (ExpiredJwtException ex) {
            // log the exception
        } catch (UnsupportedJwtException ex) {
            // log the exception
        } catch (IllegalArgumentException ex) {
            // log the exception
        }
        return false;
    }

    /**
     * Retrieve the username from a JWT token.
     *
     * @param token the JWT token
     * @return the username contained in the token
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // Additional methods can be added as needed
}
