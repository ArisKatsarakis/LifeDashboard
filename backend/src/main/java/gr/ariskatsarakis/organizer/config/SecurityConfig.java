package gr.ariskatsarakis.organizer.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import gr.ariskatsarakis.organizer.jwt.JwtAuthEntryPoint;
import gr.ariskatsarakis.organizer.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JwtAuthEntryPoint authEntryPoint;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public JwtAuthenticationFilter authenticationJwtTokenFilter() {
                return new JwtAuthenticationFilter();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                String[] servers = new String[] {
                                "http://localhost:3000",
                                "http://localhost:8081"
                };
                configuration.setAllowedOrigins(Arrays.asList(servers));
                configuration.setAllowedMethods(Arrays.asList("*"));
                configuration.setAllowedHeaders(Arrays.asList("*"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> {
                                        csrf.disable();
                                })
                                .cors(cors -> {
                                        cors.configurationSource(corsConfigurationSource());
                                })
                                .exceptionHandling(
                                                exception -> {
                                                        exception.authenticationEntryPoint(authEntryPoint);
                                                })
                                .sessionManagement(
                                                sessionManagement -> {
                                                        sessionManagement.sessionCreationPolicy(
                                                                        SessionCreationPolicy.STATELESS);
                                                })
                                .authorizeHttpRequests(auth -> {
                                        auth
                                                        .requestMatchers("/auth", "/register").permitAll()
                                                        .anyRequest().authenticated();
                                });

                http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
                return http.build();

        }

}
