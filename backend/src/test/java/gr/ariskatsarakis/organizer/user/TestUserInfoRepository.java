package gr.ariskatsarakis.organizer.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestUserInfoRepository {

        @Autowired
        private UserInfoRepository userInfoRepository;

        @Test
        @Transactional
        @Rollback
        public void TestFetchUser() {
                String email = "kats@email.com";

                Optional<UserInfo> userInfo = userInfoRepository.findByEmail(email);

                assertNotNull(userInfo.get());
        }

}
