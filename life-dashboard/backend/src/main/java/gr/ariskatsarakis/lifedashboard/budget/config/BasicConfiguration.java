package gr.ariskatsarakis.lifedashboard.budget.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicConfiguration {
  /***
   * Basic authentication configuratio
   */
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests(
        auth -> {
          auth.anyRequest().authenticated();
        });

    httpSecurity.sessionManagement(
        session -> {
          session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
    httpSecurity.httpBasic();
    httpSecurity.csrf().disable();
    return httpSecurity.build();
  }

  /**
   * Users cereated here for inline memory
   */
  @Bean
  public UserDetailsService getUserDetailsService() {
    var user = User.withUsername("user").password("{noop}user").roles("USER").build();
    var admin = User.withUsername("admin").password("{noop}user").roles("ADMIN").build();
    return new InMemoryUserDetailsManager(user, admin);
  }

  /**
   * Datasource configuration by datasource we connect the authentication to the
   * db in order to save the user accounts there
   */

}
