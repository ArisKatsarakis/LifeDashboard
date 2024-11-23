package gr.ariskatsarakis.lifedashboard.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 */
@Configuration
@EnableWebSecurity
public class WebConfig {
  @Bean
  public DataSource dataSource() {
    DataSource ds = DataSourceBuilder.create()
        .url("jdbc:postgresql://postgresdb:5432/postgres")
        .username("postgres")
        .password("postgres")
        .build();
    DatabasePopulatorUtils.execute(createDatabasePopulator(), ds);
    return ds;
  }

  private DatabasePopulator createDatabasePopulator() {
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.setContinueOnError(true);
    databasePopulator.addScript(new ClassPathResource(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION));
    return databasePopulator;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    DataSource ds = dataSource();
    UserDetails userdetails = User.builder()
        .username("katsar")
        .password(passwordEncoder().encode("test"))
        .roles("ADMIN")
        .build();
    JdbcUserDetailsManager userManager = new JdbcUserDetailsManager(ds);
    return new InMemoryUserDetailsManager(userdetails);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
    return builder.getAuthenticationManager();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
      }
    };
  }
}
