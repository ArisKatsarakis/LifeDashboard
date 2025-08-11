package gr.ariskatsarakis.organizer.savinggoals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.exception.SavingGoalNotFoundException;
import gr.ariskatsarakis.organizer.user.UserInfo;
import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SavingGoalService {

        private SavingGoalRepository savingGoalRepository;
        private UserInfoService userInfoService;

        public SavingGoalService(
                        SavingGoalRepository savingGoalRepository,
                        UserInfoService userInfoService) {
                this.savingGoalRepository = savingGoalRepository;
                this.userInfoService = userInfoService;
        }

        public List<SavingGoal> fetchSavingGoalsByUser() {
                UserInfo userInfo = userInfoService.fetchUser();

                if (userInfo == null) {
                        throw new UsernameNotFoundException("Username not found ");
                }

                return savingGoalRepository.findByUserInfo(userInfo);

        }

        public SavingGoal addSavingGoal(SavingGoal savingGoal) {

                savingGoal.setSavingGoalId(null);
                UserInfo userInfo = userInfoService.fetchUser();
                if (userInfo == null) {
                        throw new UsernameNotFoundException("Username not found");
                }

                savingGoal.setUserInfo(userInfo);
                SavingGoal s = savingGoalRepository.save(savingGoal);
                return s;
        }

        public SavingCalculations fetchCalcualtions(Long savingGoalId) {
                SavingCalculations sCalculations = new SavingCalculations();
                // Fetch Saving Goal
                Optional<SavingGoal> savingGoal = savingGoalRepository.findById(savingGoalId);
                if (savingGoal.isEmpty()) {
                        throw new SavingGoalNotFoundException(savingGoalId);
                }
                SavingGoal goal = savingGoal.get();
                int days = goal.getStartFrom().until(goal.getFinishTo()).getDays();
                System.out.println(String.format("Days of goal:  %d", days));
                UserInfo userInfo = userInfoService.fetchUser();
                if (userInfo == null) {
                        throw new UsernameNotFoundException("username not found");
                }
                // Calculate days
                // Calculate daily spending by finding what is left and divide by the days.
                BigDecimal pending = userInfo.getTotalMoneyPending();
                BigDecimal dailySpending = pending.divide(BigDecimal.valueOf(days));
                sCalculations.setDaysLeft(Long.valueOf(days));
                sCalculations.setDailySpending(dailySpending);

                return sCalculations;
        }

}
