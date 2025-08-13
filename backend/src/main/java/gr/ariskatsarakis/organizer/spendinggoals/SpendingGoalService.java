package gr.ariskatsarakis.organizer.spendinggoals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.exception.InSuffecientPendingAmmount;
import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.savinggoals.SavingGoal;
import gr.ariskatsarakis.organizer.user.UserInfo;
import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SpendingGoalService {

        private SpendingGoalRepository spendingGoalRepository;
        private UserInfoService userInfoService;

        public SpendingGoalService(
                        SpendingGoalRepository spendingGoalRepository,
                        UserInfoService userInfoService) {

                this.spendingGoalRepository = spendingGoalRepository;
                this.userInfoService = userInfoService;
        }

        public List<SpendingGoal> createSpendingGoalsAccordingToSavingGoal(SavingGoal savingGoal) {
                List<SpendingGoal> spendingGoals = spendingGoalRepository
                                .findBySavingGoalOrderBySpendingDayAsc(savingGoal);
                if (spendingGoals.size() > 0) {
                        return spendingGoals;
                }
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
                        sp.setDaySpented(BigDecimal.ZERO);
                        sp = spendingGoalRepository.save(sp);
                        spendingGoals.add(sp);

                }

                return spendingGoals;

        }

        public List<SpendingGoal> clearSpendingGoals(SavingGoal savingGoal) {
                List<SpendingGoal> spendingGoals = spendingGoalRepository.findBySavingGoal(savingGoal);
                spendingGoals.stream().forEach(s -> {
                        spendingGoalRepository.delete(s);
                });
                return spendingGoals;
        }

        public List<SpendingGoal> updateSpendingGoalsCauseOfExpense(Expense expense) {
                LocalDate dateCreated = LocalDate.ofInstant(expense.getDateCreated().toInstant(),
                                ZoneId.systemDefault());
                List<SpendingGoal> userSpendingGoals = spendingGoalRepository
                                .findByUserInfoAndSpendingDayAfterOrderBySpendingDayAsc(
                                                userInfoService.fetchUser(), dateCreated.minusDays(1l));
                BigDecimal spentLeft = expense.getMoney();
                for (int i = 0; i < userSpendingGoals.size(); i++) {
                        if (spentLeft.compareTo(BigDecimal.ZERO) != 1) {
                                break;
                        }

                        SpendingGoal goal = userSpendingGoals.get(i);
                        BigDecimal spented = BigDecimal.ZERO;
                        if (i == 0) {
                                goal.setDaySpented(expense.getMoney());
                        } else {
                                spented = spentLeft.subtract(goal.getSpendingMoney())
                                                .compareTo(BigDecimal.ZERO) == 1 ? goal.getSpendingMoney()
                                                                : spentLeft;
                                spented = spented.add(goal.getDaySpented());

                                goal.setDaySpented(spented);

                        }
                        spentLeft = spentLeft.subtract(goal.getSpendingMoney());
                        spendingGoalRepository.save(goal);
                        userSpendingGoals.set(i, goal);

                }
                return userSpendingGoals;
        }

        public void updateSpendingGoalsAccordingToSavingGoal(SavingGoal savingGoal,
                        List<SpendingGoal> oldSpendingGoals) {
                List<SpendingGoal> newSpendingGoals = createSpendingGoalsAccordingToSavingGoal(savingGoal);
                for (SpendingGoal g : oldSpendingGoals) {
                        SpendingGoal dayGoal = newSpendingGoals.stream()
                                        .filter(s -> s.getSpendingDay().equals(g.getSpendingDay())).findFirst()
                                        .orElse(null);
                        if (dayGoal == null)
                                continue;
                        dayGoal.setDaySpented(g.getDaySpented());
                        spendingGoalRepository.save(dayGoal);
                }

        }

}
