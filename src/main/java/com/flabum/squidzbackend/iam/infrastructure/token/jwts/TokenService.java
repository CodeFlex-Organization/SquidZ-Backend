package com.flabum.squidzbackend.iam.infrastructure.token.jwts;

public interface TokenService {

    String generateToken(String username);

    String getUsernameFromToken(String token);

    boolean validateToken(String token);
}
