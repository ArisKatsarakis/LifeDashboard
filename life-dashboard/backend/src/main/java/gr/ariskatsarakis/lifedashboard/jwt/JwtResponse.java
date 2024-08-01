package gr.ariskatsarakis.lifedashboard.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JwtResponse
 */
public class JwtResponse {

  @JsonProperty("username")
  private String username;
  @JsonProperty("token")
  private String token;

  public JwtResponse(String username, String token) {
    this.username = username;
    this.token = token;
  }

  public JwtResponse() {

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
