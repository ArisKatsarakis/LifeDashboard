package gr.ariskatsarakis.lifedashboard.budget.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * TODO Make the fucking authentication
 */
@Configuration
public class BasicConfiguration {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests(
        req -> {
          req.anyRequest().authenticated();
        });
    httpSecurity.sessionManagement(
        ses -> {
          ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
    // httpSecurity.httpBasic(withDefaults());
    System.out.println("getting through ");
    httpSecurity.csrf(
        csrf -> csrf.disable());
    return httpSecurity.build();
  }
}
