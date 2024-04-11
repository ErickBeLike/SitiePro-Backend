package com.api.sitiepro.service;

import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.repository.TokenRepositoryAdmin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceAdmin {
    private String SECRET_KEY_ADMIN = "8c5a5e65cca4c3255d76b298f81dcc60c12be705f0f6f7ba408ef74077bb9f4c";

    private final TokenRepositoryAdmin tokenRepositoryAdmin;

    public JwtServiceAdmin(TokenRepositoryAdmin tokenRepositoryAdmin) {
        this.tokenRepositoryAdmin = tokenRepositoryAdmin;
    }

    public String generatedTokenAdmin(Usuarios usuarios) {
        String token = Jwts
                .builder()
                .subject(usuarios.getCorreoUsuario())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    public boolean isValid(String token, UserDetails usuarios) {
        String username = extractUsername(token);

        boolean isValidTokenAdmin = tokenRepositoryAdmin.findByTokenAdmin(token)
                .map(t->!t.isLoggedOutAdmin()).orElse(false);

        return (username.equals(usuarios.getUsername())) && !isTokenExpired(token) && isValidTokenAdmin;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token ) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY_ADMIN);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
