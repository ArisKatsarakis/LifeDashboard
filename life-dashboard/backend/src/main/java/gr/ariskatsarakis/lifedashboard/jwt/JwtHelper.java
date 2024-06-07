package gr.ariskatsarakis.lifedashboard.jwt;

import org.springframework.stereotype.Component;

/**
 * JwtHelper
 */
@Component
public class JwtHelper {
  public static final long JWT_TOKEN_DURATION = 5 * 60 * 60;
  private String secret = "javainuse";

}
