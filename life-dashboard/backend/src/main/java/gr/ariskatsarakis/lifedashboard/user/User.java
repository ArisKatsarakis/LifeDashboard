package gr.ariskatsarakis.lifedashboard.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_table")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long userId;
  private String username;
  private String password;

  public User() {

  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public Long getUserId() {
    return userId;
  }

}
