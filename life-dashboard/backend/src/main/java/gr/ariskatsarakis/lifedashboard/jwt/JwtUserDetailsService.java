package gr.ariskatsarakis.lifedashboard.jwt;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * JwtUserDetailsService
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

  public UserDetails loadUserByUsername(String username) {
    if ("javainuse".equals(username)) {
      return new User("javainuse", "password", new ArrayList<>());
    } else {
      throw new UsernameNotFoundException(username + " : User not found ! ");
    }
  }
}
