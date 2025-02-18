package gr.ariskatsarakis.lifedashboard.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.registration.RegistrationRequest;
import gr.ariskatsarakis.lifedashboard.registration.RegistrationResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AppUserService implements UserDetailsService {
  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
  private AppUserRepository appUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found"));

  }

  // TODO fix this finish the registration.
  public ResponseEntity<RegistrationResponse> registerUser(RegistrationRequest request) {
    logger.debug(String.format("Request inboud: %s", request.toString()));

    AppUser appUser = new AppUser();
    appUser.setUsername(request.getUsername());
    appUser.setPassword(request.getPassword());
    appUser.setEmail(request.getEmail());

    return new ResponseEntity<RegistrationResponse>(new RegistrationResponse("user already exists "), HttpStatus.FOUND);
  }

}
