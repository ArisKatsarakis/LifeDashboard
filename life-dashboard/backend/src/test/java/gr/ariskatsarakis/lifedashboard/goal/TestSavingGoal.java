package gr.ariskatsarakis.lifedashboard.goal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * TestSavingGoal
 */
public class TestSavingGoal {

  @Test
  void createSavingGoal_Test() {
    SavingGoal goal = new SavingGoal();

    goal.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
    goal.setEndDate(Timestamp.valueOf(LocalDateTime.now().plusDays(1)));
    goal.setTarget(BigDecimal.TEN);

  }
}
