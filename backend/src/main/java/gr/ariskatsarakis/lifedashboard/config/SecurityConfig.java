package gr.ariskatsarakis.lifedashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gr.ariskatsarakis.lifedashboard.jwt.JwtAutenticationEntryPoint;
import gr.ariskatsarakis.lifedashboard.jwt.JwtAuthenticationFilter;
import gr.ariskatsarakis.lifedashboard.user.AppUserService;
import lombok.AllArgsConstructor;

/**
 * SecurityConfig
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final AppUserService appUserService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtAuthenticationFilter filter;
  private final JwtAutenticationEntryPoint point;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((auth) -> {
          auth.requestMatchers("/api/v*/registration/**", "/api/v*/registration/confirm/**", "/auth/login/**")
              .permitAll();
          auth.anyRequest().authenticated();
        }).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(ex -> ex.authenticationEntryPoint(point));
    return http.build();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(appUserService);
    daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(
      UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder) {

    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(appUserService);
    authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
    return new ProviderManager(authenticationProvider);
  }

  public AppUserService getAppUserService() {
    return appUserService;
  }
}
