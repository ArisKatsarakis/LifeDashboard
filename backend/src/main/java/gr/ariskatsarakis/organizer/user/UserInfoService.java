package gr.ariskatsarakis.organizer.user;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
        private final UserInfoRepository repository;
        private final PasswordEncoder pEncoder;

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
                this.repository = repository;
                this.pEncoder = encoder;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<UserInfo> userInfo = repository.findByEmail(username);
                if (userInfo.isEmpty()) {
                        throw new UsernameNotFoundException("User not found with email: " + username);
                }
                UserInfoDetails user = new UserInfoDetails(userInfo.get());
                User userDetails = new User(user.getUsername(), user.getPassword(), user.getAuthorities());
                return userDetails;

        }

        public String addUser(UserInfo userInfo) {
                // userInfo.setPassword(pEncoder.encode(userInfo.getPassword()));
                repository.save(userInfo);
                return "User added successfully";

        }

        public UserInfo fetchUser(String email) {
                Optional<UserInfo> optional = repository.findByEmail(email);
                if (optional.isEmpty()) {
                        return null;
                }
                return optional.get();

        }

}
