package dsm.hackaton._8.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import dsm.hackaton._8.global.error.exception.DSMException;
import dsm.hackaton._8.global.error.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (DSMException e) {
            ErrorCode errorCode = e.getErrorCode();
            writeErrorResponse(response, ErrorResponse.of(errorCode.getMessage(), errorCode.getStatusCode()));
        } catch (Exception e) {
            writeErrorResponse(response, ErrorResponse.of(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    private void writeErrorResponse(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
