package gr.ariskatsarakis.organizer.savinggoals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import gr.ariskatsarakis.organizer.spendinggoals.SpendingGoal;
import gr.ariskatsarakis.organizer.spendinggoals.SpendingGoalRepository;
import gr.ariskatsarakis.organizer.spendinggoals.SpendingGoalService;
import gr.ariskatsarakis.organizer.user.UserInfo;
import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestSavingGoalService {

        @Mock
        private UserInfoService userInfoService;

        @Mock
        private SpendingGoalRepository spendingGoalRepository;

        @InjectMocks
        private SpendingGoalService spendingGoalService;

        @Test
        @Transactional
        @Rollback
        public void Test_createSpendingGoalsForSavingGoal() {
                UserInfo userInfo = new UserInfo();
                userInfo.setTotalMoneyPending(BigDecimal.valueOf(500));

                when(userInfoService.fetchUser()).thenReturn(userInfo);
                when(spendingGoalRepository.save(Mockito.any(SpendingGoal.class))).thenAnswer(i -> {
                        SpendingGoal spendingGoal = i.getArgument(0);
                        spendingGoal.setSpendingGoalId(0l);
                        return spendingGoal;
                });

                SavingGoal mockGoal = new SavingGoal();
                mockGoal.setStartFrom(LocalDate.now());
                mockGoal.setFinishTo(LocalDate.now().plusDays(8l));
                mockGoal.setSavingGoalId(10000l);
                mockGoal.setSavingGoalMoney(BigDecimal.valueOf(200l));

                List<SpendingGoal> spendingGoals = spendingGoalService
                                .createSpendingGoalsAccordingToSavingGoal(mockGoal);
                assertTrue(spendingGoals.size() == 8);
                BigDecimal expectedGoal = BigDecimal.valueOf(500).subtract(mockGoal.getSavingGoalMoney())
                                .divide(BigDecimal.valueOf(8l));
                for (int i = 0; i < spendingGoals.size(); i++) {
                        assertTrue(spendingGoals.get(i).getSpendingMoney().equals(expectedGoal));
                        // This is a mocking assertion to see if the mocking works ok
                        assertTrue(spendingGoals.get(i).getSpendingGoalId() == 0l);
                }

        }
}
