package gr.ariskatsarakis.organizer.spendinggoals;

import java.math.BigDecimal;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.exception.InSuffecientPendingAmmount;
import gr.ariskatsarakis.organizer.savinggoals.SavingGoal;
import gr.ariskatsarakis.organizer.user.UserInfo;
import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SpendingGoalService {

        private SpendingGoalRepository spendingGoalRepository;
        private UserInfoService userInfoService;

        public SpendingGoalService(SpendingGoalRepository spendingGoalRepository,
                        UserInfoService userInfoService) {

                this.spendingGoalRepository = spendingGoalRepository;
                this.userInfoService = userInfoService;
        }

        public List<SpendingGoal> createSpendingGoalsAccordingToSavingGoal(SavingGoal savingGoal) {
                List<SpendingGoal> spendingGoals = new ArrayList<>();
                Period period = savingGoal.getStartFrom().until(savingGoal.getFinishTo());
                UserInfo userInfo = userInfoService.fetchUser();

                if (userInfo == null) {
                        throw new UsernameNotFoundException("Username not found");
                }

                for (int i = 0; i < period.getDays(); i++) {
                        SpendingGoal sp = new SpendingGoal();
                        sp.setSpendingDay(savingGoal.getStartFrom().plusDays(i));
                        BigDecimal totalPending = userInfo.getTotalMoneyPending();
                        totalPending = totalPending.subtract(savingGoal.getSavingGoalMoney());
                        if (totalPending.equals(BigDecimal.ZERO)) {
                                throw new InSuffecientPendingAmmount(userInfo, savingGoal.getSavingGoalId());
                        }
                        sp.setSpendingMoney(totalPending.divide(BigDecimal.valueOf(period.getDays())));
                        sp.setUserInfo(userInfo);
                        sp.setSavingGoal(savingGoal);
                        sp = spendingGoalRepository.save(sp);
                        spendingGoals.add(sp);

                }

                return spendingGoals;

        }

        public void clearSpendingGoals(SavingGoal savingGoal) {
                List<SpendingGoal> spendingGoals = spendingGoalRepository.findBySavingGoal(savingGoal);
                spendingGoals.stream().forEach(s -> {
                        spendingGoalRepository.delete(s);
                });
        }
}
