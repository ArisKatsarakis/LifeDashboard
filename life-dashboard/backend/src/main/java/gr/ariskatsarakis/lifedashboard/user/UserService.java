package gr.ariskatsarakis.lifedashboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public List<User> addUserGetAll(User user) {
    userRepository.save(user);
    return userRepository.findAll();
  }
}
