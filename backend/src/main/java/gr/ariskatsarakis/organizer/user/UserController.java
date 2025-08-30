package gr.ariskatsarakis.organizer.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gr.ariskatsarakis.organizer.jwt.JwtService;
import gr.ariskatsarakis.organizer.user.request.LoginRequest;
import gr.ariskatsarakis.organizer.user.request.LoginResponse;
import gr.ariskatsarakis.organizer.user.request.RegisterRequest;
import gr.ariskatsarakis.organizer.user.request.RegisterResponse;

@RestController
public class UserController {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private UserInfoService userDetailsService;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private JwtService jwtService;

        @PostMapping("/register")
        public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
                RegisterResponse regResponse = new RegisterResponse();
                try {
                        userDetailsService.loadUserByUsername(registerRequest.getEmail());
                        regResponse.setMessage("user found");
                        return new ResponseEntity<>(regResponse, HttpStatus.CONFLICT);
                } catch (Exception e) {
                        logger.info(e.getMessage());
                }

                UserInfo newUser = new UserInfo();
                newUser.setName(registerRequest.getUsername());
                newUser.setEmail(registerRequest.getEmail());
                newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                newUser.setRoles("USER");

                String response = userDetailsService.addUser(newUser);
                regResponse.setMessage(response);
                regResponse.setStatus(201l);
                return new ResponseEntity<>(regResponse, HttpStatus.CREATED);

        }

        @PostMapping("/auth")
        public ResponseEntity<LoginResponse> generateJwt(@RequestBody LoginRequest loginRequest) {

                LoginResponse response = new LoginResponse();
                try {

                        UserDetails userInfo = userDetailsService.loadUserByUsername(loginRequest.getUsername());
                        logger.info(userInfo.toString());
                        Authentication authentication = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        loginRequest.getUsername(),
                                                        loginRequest.getPassword()));

                        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                        String token = jwtService.generateToken(userDetails.getUsername());
                        response.setJwtToken(token);
                        response.setUsername(userDetails.getUsername());
                } catch (Exception e) {
                        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

                }
                return new ResponseEntity<>(response, HttpStatus.OK);

        }

        @GetMapping("api/v1/user/finance")
        public ResponseEntity<UserInfoFinanceDTO> fetchUserFinanceDTO() {
                return new ResponseEntity<>(userDetailsService.fetchFinance(), HttpStatus.OK);
        }

}
