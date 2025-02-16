package gr.ariskatsarakis.lifedashboard.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AppUserService implements UserDetailsService {
  private AppUserRepository appUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
  }

  public void registerUser(RegistrationRequest request) {
    // TODO register user in repository
    throw new UnsupportedOperationException("Unimplemented method 'registerUser'");
  }

}
