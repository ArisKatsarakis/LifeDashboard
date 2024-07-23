package gr.ariskatsarakis.lifedashboard.jwt;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import gr.ariskatsarakis.lifedashboard.config.WebConfig;

/**
 * TestJwtHelper
 */
public class TestJwtHelper {

  @Autowired
  private JwtHelper sut = new JwtHelper();

  WebConfig webConfig = new WebConfig();

  @Autowired
  private UserDetailsService userDetailsService = webConfig.userDetailsService();

  @Test
  public void text_doGenerateToken() {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername("katsar");
    String token = this.sut.generateToken(userDetails);
    System.out.println("Bearer: " + token);
    assert ("katsar".equals(this.sut.getUsernameFromToken(token)));
  }
}
