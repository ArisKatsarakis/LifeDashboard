package gr.ariskatsarakis.lifedashboard.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("Select u from User u where u.username = ?1")
  Optional<User> getUserByUsername(String username);

}
