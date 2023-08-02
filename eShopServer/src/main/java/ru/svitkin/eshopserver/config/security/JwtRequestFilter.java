package ru.svitkin.eshopserver.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.svitkin.eshopserver.utils.JwtTokenUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        String username = null;
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            try {
                username = jwtTokenUtils.getUsername(token);
            } catch (ExpiredJwtException exception) {
                log.debug("Время жизни токена вышло");
            } catch (SignatureException exception) {
                log.debug("Подпись не подтверждена");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username, null, jwtTokenUtils.getRoles(token).stream().map(SimpleGrantedAuthority::new).toList()
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
