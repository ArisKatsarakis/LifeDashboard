package gr.ariskatsarakis.lifedashboard.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SavingGoalRepository
 */
@Repository
public interface SavingGoalRepository extends JpaRepository<SavingGoal, Long> {
  // Silence is Gold

}
