package gr.ariskatsarakis.organizer.spendinggoals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.user.UserInfo;
import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestSpendingGoalService {
        @Mock
        private UserInfoService userInfoService;

        @Mock
        private SpendingGoalRepository spendingGoalRepository;

        @InjectMocks
        private SpendingGoalService spendingGoalService;

        @Test
        @Transactional
        @Rollback
        public void Test_updateSpendingGoalsCauseOfExpense() {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(7);
                when(userInfoService.fetchUser()).thenReturn(userInfo);
                when(spendingGoalRepository.save(Mockito.any(SpendingGoal.class))).thenAnswer(i -> {
                        SpendingGoal goal = i.getArgument(0);
                        goal.setSpendingGoalId(0l);
                        return goal;
                });

                when(spendingGoalRepository.findByUserInfoAndSpendingDayAfterOrderBySpendingDayAsc(userInfo,
                                LocalDate.now().minusDays(1l))).thenReturn(userSpendingGoals());
                Expense expense = new Expense();
                expense.setMoney(BigDecimal.valueOf(45l));
                Date datecreated = new Date();
                expense.setDateCreated(datecreated);
                expense.setExpenseId(1l);

                List<SpendingGoal> spendingGoals = spendingGoalService.updateSpendingGoalsCauseOfExpense(expense);
                assertTrue(spendingGoals.size() > 0);
                // asert order with first spending goals is the same with today
                assertTrue(spendingGoals.get(0).getSpendingDay().equals(LocalDate.now()));
                for (SpendingGoal sp : spendingGoals) {
                        assertNotEquals(LocalDate.now().minusDays(1l), sp.getSpendingDay());
                        if (sp.getSpendingDay().equals(LocalDate.now())) {
                                assertEquals(expense.getMoney(), sp.getDaySpented());
                                continue;
                        }
                        assertTrue(sp.getDaySpented().compareTo(expense.getMoney()) != 1);
                }

        }

        private List<SpendingGoal> userSpendingGoals() {
                List<SpendingGoal> spendingGoals = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                        LocalDate dateSpent = LocalDate.now().plusDays(i);
                        SpendingGoal goal = new SpendingGoal();
                        goal.setSpendingDay(dateSpent);
                        goal.setDaySpented(BigDecimal.ZERO);
                        goal.setSpendingMoney(BigDecimal.TEN);
                        spendingGoals.add(goal);

                }
                return spendingGoals;
        }

}
