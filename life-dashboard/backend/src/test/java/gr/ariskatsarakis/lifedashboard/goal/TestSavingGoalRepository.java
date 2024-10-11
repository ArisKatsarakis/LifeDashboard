package gr.ariskatsarakis.lifedashboard.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import gr.ariskatsarakis.lifedashboard.wallet.Wallet;
import gr.ariskatsarakis.lifedashboard.wallet.WalletRepository;

/**
 * TestSavingGoalRepository
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestSavingGoalRepository {

  @Autowired
  private SavingGoalRepository sut;

  @Autowired(required = true)
  private WalletRepository walletRepository;
  private SavingGoalService savingGoalService;

  @Test
  void test_createNewSavingGoal() throws Exception {
    Wallet wallet = new Wallet();
    wallet.setMoneyNow(BigDecimal.valueOf(29999L));
    wallet.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));

    walletRepository.save(wallet);

    savingGoalService = new SavingGoalService();
    savingGoalService.savingGoalRepository = sut;
    savingGoalService.walletRepository = walletRepository;

    SavingGoal savingGoal = new SavingGoal();
    savingGoal.setTarget(BigDecimal.TEN);
    savingGoal.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
    Timestamp endDate = Timestamp.valueOf(LocalDateTime.now().plusDays(30));
    savingGoal.setEndDate(endDate);
    /**
     * TODO
     * create inside the business service the dailyExpensesGoals for the savingGoal.
     */
    savingGoalService.createSavingGoal(savingGoal);

    List<SavingGoal> savingGoals = sut.findAll();
    assertEquals(1, savingGoals.size(), "size is equal  not!!!!");

  }
}
