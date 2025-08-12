package gr.ariskatsarakis.organizer.savinggoals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import gr.ariskatsarakis.organizer.spendinggoals.SpendingGoal;
import gr.ariskatsarakis.organizer.user.request.LoginRequest;
import gr.ariskatsarakis.organizer.user.request.LoginResponse;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class TestSavingGoalController {

        @Autowired
        private MockMvc mockMvc;

        private static String bearerToken;

        @BeforeAll
        public static void createHeader() {
                RestTemplate restTemplate = new RestTemplate();
                LoginRequest req = new LoginRequest();
                req.setUsername("username");
                req.setPassword("katsar");

                ResponseEntity<LoginResponse> response = restTemplate.postForEntity("http://localhost:8080/auth", req,
                                LoginResponse.class);
                bearerToken = response.getBody().getJwtToken();
        }

        @Test
        @Transactional
        @Rollback
        public void Test_fetchingCalculation() throws Exception {

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setBearerAuth(bearerToken);
                httpHeaders.set("Authorization", "Bearer " + bearerToken);
                RestTemplate restTemplate = new RestTemplate();

                HttpEntity<List<SavingGoal>> httpEntity = new HttpEntity<List<SavingGoal>>(httpHeaders);
                ResponseEntity<SavingGoal[]> response = restTemplate.exchange(
                                "http://localhost:8080/api/v1/saving-goals",
                                HttpMethod.GET,
                                httpEntity,
                                SavingGoal[].class);
                assertTrue(response.getBody().length > 0);
                for (int i = 0; i < response.getBody().length; i++) {
                        System.out.println(String
                                        .format("Creating expense goals for walletId: %d",
                                                        response.getBody()[i].getSavingGoalId()));

                        HttpEntity<List<SpendingGoal>> spendingHttpEntity = new HttpEntity<List<SpendingGoal>>(
                                        httpHeaders);
                        ResponseEntity<SpendingGoal[]> responseSpending = restTemplate.exchange(
                                        "http://localhost:8080/api/v1/saving-goals/"
                                                        + response.getBody()[i].getSavingGoalId()
                                                        + "/create-spending-goals",
                                        HttpMethod.GET,
                                        spendingHttpEntity,
                                        SpendingGoal[].class);
                        assertTrue(responseSpending.getBody().length > 0);
                        for (int j = 0; j < responseSpending.getBody().length; j++) {
                                SpendingGoal spGoal = responseSpending.getBody()[j];
                                assertFalse(spGoal.getSpendingGoalId().equals(0l));
                                assertFalse(spGoal.getSpendingMoney().equals(BigDecimal.ZERO));
                        }

                        ResponseEntity<String> deleteResponse = restTemplate.exchange(
                                        "http://localhost:8080/api/v1/saving-goals/"
                                                        + response.getBody()[i].getSavingGoalId()
                                                        + "/create-spending-goals",
                                        HttpMethod.DELETE,
                                        spendingHttpEntity,
                                        String.class);
                        System.out.println(deleteResponse.getBody());
                }

        }

}
