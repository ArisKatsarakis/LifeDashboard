package gr.ariskatsarakis.lifedashboard.registration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.ariskatsarakis.lifedashboard.user.AppUser;
import gr.ariskatsarakis.lifedashboard.user.AppUserRepository;
import gr.ariskatsarakis.lifedashboard.user.AppUserService;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  @Autowired
  private AppUserRepository appUserRepository;
  @Autowired
  private AppUserService appUserService;

  @PostMapping
  public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest request) {
    // check if user exists
    Optional<AppUser> optionalUser = appUserRepository.findByUsername(request.getUsername());
    if (optionalUser.isEmpty()) {
      return appUserService.registerUser(request);
    }

    return new ResponseEntity<RegistrationResponse>(new RegistrationResponse("user already exists "), HttpStatus.FOUND);
  }
}
