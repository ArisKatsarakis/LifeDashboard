package gr.ariskatsarakis.lifedashboard.jwt;

/**
 * JwtResponse
 */
public class JwtResponse {

  private String username;
  private String token;

  public JwtResponse(String username, String token) {
    this.username = username;
    this.token = token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getToken() {
    return token;
  }

  public String getUsername() {
    return username;
  }

}
