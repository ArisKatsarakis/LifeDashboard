package gr.ariskatsarakis.organizer.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) throws IOException, ServletException {

                handleException(response, request, authException.getMessage());

        }

        private void handleException(HttpServletResponse response, HttpServletRequest request, String message)
                        throws IOException {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                Map<String, Object> errorDetails = new HashMap<>();
                errorDetails.put("location", request.getRequestURI());
                errorDetails.put("timestamp", LocalDate.now().toString());
                errorDetails.put("errorMessage", message);

                response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
        }
}
