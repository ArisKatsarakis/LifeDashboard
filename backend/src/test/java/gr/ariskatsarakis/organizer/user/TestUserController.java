package gr.ariskatsarakis.organizer.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.ariskatsarakis.organizer.user.request.RegisterRequest;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TestUserController {
        @Autowired
        private MockMvc mockMvc;

        @Test
        @Transactional
        @Rollback
        public void testRegisterUser() throws Exception {
                RegisterRequest request = new RegisterRequest();
                request.setEmail("aris@email.com");
                request.setPassword("password");
                request.setUsername("username");

                // Already registered user testing
                mockMvc.perform(post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                                .andExpect(status().is(500)); // Adjust based on expected status
        }
}
