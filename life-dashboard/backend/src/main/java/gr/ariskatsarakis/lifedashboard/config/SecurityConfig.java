package gr.ariskatsarakis.lifedashboard.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import gr.ariskatsarakis.lifedashboard.jwt.JwtAutenticationEntryPoint;
import gr.ariskatsarakis.lifedashboard.jwt.JwtAuthenticationFilter;

/**
 * SecurityConfig
 */
@Configuration
public class SecurityConfig {

  @Autowired
  private JwtAuthenticationFilter filter;

  @Autowired
  private JwtAutenticationEntryPoint point;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            authorize -> {
              authorize.requestMatchers("/api/v1/expenses").authenticated().requestMatchers("/auth/login").permitAll()
                  .anyRequest().authenticated();
            })
        .cors(cors -> cors.configurationSource(request -> {
          CorsConfiguration configuration = new CorsConfiguration();
          configuration.setAllowedOrigins(Arrays.asList("*"));
          configuration.setAllowedMethods(Arrays.asList("*"));
          configuration.setAllowedHeaders(Arrays.asList("*"));
          return configuration;
        }))
        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

}
