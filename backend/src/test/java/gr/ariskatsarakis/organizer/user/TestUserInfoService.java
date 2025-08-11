package gr.ariskatsarakis.organizer.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;

@ExtendWith(MockitoExtension.class)
public class TestUserInfoService {

        @Mock
        private UserInfoRepository userInfoRepository;

        /**
         * Subject under test*
         */
        @InjectMocks
        private UserInfoService userInfoService;

        private static final String USERNAME = "username";
        private static final String USER_EMAIL = "ariskatsarkis@hotmail.com";

        @Test
        @Transactional
        @Rollback
        public void registerUser() {
                when(userInfoRepository.findByEmail(USER_EMAIL)).thenReturn(null);

                UserInfo userInfo = new UserInfo();
                userInfo.setEmail(USER_EMAIL);
                userInfo.setName(USERNAME);
                userInfo.setRoles("USER");

                String userInfoStr = userInfoService.addUser(userInfo);
                assertNotNull(userInfoStr);
                assertSame("User added successfully", userInfoStr);

        }

}
