package ru.svitkin.eshopserver.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        claims.put("roles", roles);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + lifetime.toMillis());
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getAllClaims(String token) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        return getAllClaims(token).get("roles", List.class);
    }

    public String getUsername(String token) {
        return getAllClaims(token).getSubject();
    }
}
