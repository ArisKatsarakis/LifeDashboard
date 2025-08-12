package gr.ariskatsarakis.organizer.spendinggoals;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.ariskatsarakis.organizer.savinggoals.SavingGoal;
import gr.ariskatsarakis.organizer.user.UserInfo;

@Repository
public interface SpendingGoalRepository extends JpaRepository<SpendingGoal, Long> {

        List<SpendingGoal> findByUserInfo(UserInfo userInfo);

        List<SpendingGoal> findBySavingGoal(SavingGoal savingGoal);
}
