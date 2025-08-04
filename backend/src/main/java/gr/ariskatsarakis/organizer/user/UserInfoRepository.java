package gr.ariskatsarakis.organizer.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

        // Email Query for users;
        Optional<UserInfo> findByEmail(String email);

}
