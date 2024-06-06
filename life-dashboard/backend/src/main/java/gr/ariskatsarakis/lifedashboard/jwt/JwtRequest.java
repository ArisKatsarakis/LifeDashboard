package gr.ariskatsarakis.lifedashboard.jwt;

import java.io.Serializable;

/**
 * JwtRequest
 */
public class JwtRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  private String username;
  private String password;

  public JwtRequest() {

  }

  public JwtRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
