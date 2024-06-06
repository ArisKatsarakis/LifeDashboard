package gr.ariskatsarakis.lifedashboard.jwt;

import java.io.Serializable;

/**
 * JwtResponse
 */

public class JwtResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private final String jwtToken;

  public JwtResponse(String token) {
    this.jwtToken = token;

  }

  public String getJwtToken() {
    return jwtToken;
  }
}
