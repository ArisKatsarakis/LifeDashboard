package gr.ariskatsarakis.lifedashboard.goal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * DailyExpenseGoalRepository
 */
@Repository
public interface DailyExpenseGoalRepository extends JpaRepository<DailyExpenseGoal, Long> {

  @Query("SELECT d FROM DailyExpenseGoal d where d.savingGoal = ?1")
  List<DailyExpenseGoal> findBySavingGoalId(SavingGoal savingGoal);
}
