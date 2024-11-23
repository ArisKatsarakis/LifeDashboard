package gr.ariskatsarakis.lifedashboard.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * TestSavingGoal
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestSavingGoal {

  @Autowired
  private DailyExpenseGoalRepository repository;

  @Test
  void createSavingGoal_Test() {

    DailyExpenseGoal goal = new DailyExpenseGoal();
    goal.setMoneyGoal(BigDecimal.TEN);
    goal.setStatus(DailyExpenseGoalStatus.PENDING);
    goal.setRegardingDay(Timestamp.valueOf(LocalDateTime.now()));

    goal = repository.save(goal);
    List<DailyExpenseGoal> goals = repository.findAll();
    assertEquals(1L, goals.size());

  }
}
