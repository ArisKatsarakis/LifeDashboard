package gr.ariskatsarakis.organizer.spendinggoals;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.ariskatsarakis.organizer.savinggoals.SavingGoal;
import gr.ariskatsarakis.organizer.user.UserInfo;

@Repository
public interface SpendingGoalRepository extends JpaRepository<SpendingGoal, Long> {

        List<SpendingGoal> findByUserInfo(UserInfo userInfo);

        List<SpendingGoal> findBySavingGoal(SavingGoal savingGoal);

        List<SpendingGoal> findBySpendingDay(LocalDate dateCreated);

        List<SpendingGoal> findBySpendingDayAfter(LocalDate dateCreated);

        List<SpendingGoal> findBySavingGoalOrderBySpendingDayAsc(SavingGoal savingGoal);

        List<SpendingGoal> findBySpendingDayAfterOrderBySpendingDayAsc(LocalDate dateCreated);

        List<SpendingGoal> findByUserInfoAndSpendingDayAfterAndSpendingDay(UserInfo fetchUser, LocalDate dateCreated,
                        LocalDate dateCreated2);

        List<SpendingGoal> findByUserInfoAndSpendingDayAfterOrderBySpendingDayAsc(UserInfo fetchUser,
                        LocalDate dateCreated);

}
