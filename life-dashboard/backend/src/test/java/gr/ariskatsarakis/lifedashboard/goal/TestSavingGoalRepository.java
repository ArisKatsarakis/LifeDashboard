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

/**
 * TestSavingGoalRepository
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestSavingGoalRepository {

  @Autowired
  private SavingGoalRepository sut;

  private SavingGoalService savingGoalService;

  @Test
  void test_createNewSavingGoal() throws Exception {
    savingGoalService = new SavingGoalService();
    savingGoalService.savingGoalRepository = sut;
    SavingGoal savingGoal = new SavingGoal();

    savingGoal.setTarget(BigDecimal.TEN);
    savingGoal.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
    savingGoal.setEndDate(Timestamp.valueOf(LocalDateTime.now().plusDays(2)));
    savingGoalService.createSavingGoal(savingGoal);

    List<SavingGoal> savingGoals = sut.findAll();
    assertEquals(1, savingGoals.size(), "size is equal  not!!!!");

  }
}
