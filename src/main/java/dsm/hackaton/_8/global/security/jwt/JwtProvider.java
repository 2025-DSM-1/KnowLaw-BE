package dsm.hackaton._8.global.security.jwt;

import dsm.hackaton._8.domain.auth.domain.RefreshToken;
import dsm.hackaton._8.domain.auth.exceptoin.ExpiredTokenException;
import dsm.hackaton._8.domain.auth.exceptoin.InvalidTokenException;
import dsm.hackaton._8.domain.auth.presentation.dto.response.TokenResponse;
import dsm.hackaton._8.domain.auth.domain.repository.RefreshTokenRepository;
import dsm.hackaton._8.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String ACCESS_KEY = "access_token";
    private static final String REFRESH_KEY = "refresh_token";

    public TokenResponse generateToken(String email) {
        String accessToken = generateToken(email, ACCESS_KEY, jwtProperties.getAccessExp());
        String refreshToken = generateToken(email, REFRESH_KEY, jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .ttl(jwtProperties.getRefreshExp() * 1000)
                .build()
        );

        return new TokenResponse(accessToken, refreshToken);
    }

    private String generateToken(String email, String type, Long exp) {
        return Jwts.builder()
                .setSubject(email)
                .setHeaderParam("typ", type)
                .signWith(Keys.hmacShaKeyFor(
                        Base64.getDecoder().decode(jwtProperties.getSecretKey()))
                        , SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isBearerToken(bearer)) {
            return bearer.substring(jwtProperties.getPrefix().length() + 1);
        }

        return null;
    }

    public boolean isBearerToken(String token) {
        return (token != null)
                && (token.startsWith(jwtProperties.getPrefix()))
                && (token.length() > jwtProperties.getPrefix().length() + 1);
    }

    public Authentication getAuthenticationFromToken(String token) {
        Claims body = getJws(token).getBody();
        if (!isNotRefreshToken(token)) throw InvalidTokenException.EXCEPTION;

        UserDetails userDetails = getDetails(body);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean isNotRefreshToken(String token) {
        return !REFRESH_KEY.equals(getJws(token).getHeader().get("typ").toString());
    }

    private Jws<Claims> getJws(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
            SecretKey key = Keys.hmacShaKeyFor(keyBytes);

            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private UserDetails getDetails(Claims body) {
        return authDetailsService.loadUserByUsername(body.getSubject());
    }
}