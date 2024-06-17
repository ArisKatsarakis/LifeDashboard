package gr.ariskatsarakis.lifedashboard.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JwtController
 */
@RestController
@RequestMapping("/auth/login")
public class JwtController {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private JwtHelper jwtHelper;

  private Logger logger = LoggerFactory.getLogger(JwtController.class);

  @PostMapping
  @CrossOrigin
  public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

    this.doAuthenticate(request.getUsername(), request.getPassword());
    UserDetails userdetails = userDetailsService.loadUserByUsername(request.getUsername());
    String token = this.jwtHelper.generateToken(userdetails);

    JwtResponse response = new JwtResponse(userdetails.getUsername(), token);

    logger.info("Token for user: {}", token);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  private void doAuthenticate(String username, String password) {
    logger.info("authentication ");
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
        password);
    try {
      manager.authenticate(authenticationToken);
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Invalid Username or Password ! ");
    }

  }

  @ExceptionHandler(BadCredentialsException.class)
  public String exceptionHandler() {
    return "Credentials Invalid";
  }
}
