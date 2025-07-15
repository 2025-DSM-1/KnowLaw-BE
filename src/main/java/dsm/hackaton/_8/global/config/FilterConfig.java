package dsm.hackaton._8.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dsm.hackaton._8.global.error.GlobalExceptionFilter;
import dsm.hackaton._8.global.security.jwt.JwtFilter;
import dsm.hackaton._8.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter jwtTokenFilter = new JwtFilter(jwtProvider);
        GlobalExceptionFilter globalExceptionFilter = new GlobalExceptionFilter(objectMapper);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore(globalExceptionFilter, JwtFilter.class);
    }
}
