package gr.ariskatsarakis.lifedashboard.wallet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.ariskatsarakis.lifedashboard.income.Income;
import gr.ariskatsarakis.lifedashboard.jwt.JwtRequest;
import gr.ariskatsarakis.lifedashboard.jwt.JwtResponse;

/**
 * TestWalletService
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestWalletController {

  @Autowired
  MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();

  private JwtResponse jwtResponse;

  @BeforeAll
  void setup() throws Exception {

    JwtRequest input = new JwtRequest();
    input.setUsername("katsar");
    input.setPassword("test");

    MvcResult authResult = mockMvc.perform(
        post("/auth/login")
            .content(input.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
        .andReturn();
    this.jwtResponse = objectMapper.readValue(authResult.getResponse().getContentAsString(), JwtResponse.class);
  }

  @Test
  void addIncome_Test() throws Exception {
    Income i = new Income();
    i.setMoney(BigDecimal.TEN);
    i.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    mockMvc.perform(
        post("/api/v1/expense")
            .header("Authorization", "Bearer " + jwtResponse.getToken()));
    MvcResult getWalletsResult = mockMvc.perform(
        get("/api/v1/wallet")
            .header("Authorization", "Bearer " + jwtResponse.getToken()))
        .andReturn();

    Wallet[] wallets = objectMapper.readValue(getWalletsResult.getResponse().getContentAsString(), Wallet[].class);
    assertThat(wallets.length).isGreaterThan(0);
    for (Wallet w : wallets) {
      System.out.println("monney now: " + w.getMoneyNow());
    }
    MvcResult getLastWalletResult = mockMvc.perform(
        get("/api/v1/last-wallet")
            .header("Authorization", "Bearer " + jwtResponse.getToken()))
        .andReturn();

    Wallet lastWallet = objectMapper.readValue(getLastWalletResult.getResponse().getContentAsString(), Wallet.class);
    System.out.println(lastWallet.getMoneyNow());
  }

}
