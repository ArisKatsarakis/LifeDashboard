package gr.ariskatsarakis.lifedashboard.goal;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.wallet.Wallet;
import gr.ariskatsarakis.lifedashboard.wallet.WalletRepository;

/**
 * SavingGoalService
 */
@Service
public class SavingGoalService {
  @Autowired
  SavingGoalRepository savingGoalRepository;

  @Autowired
  WalletRepository walletRepository;

  @Autowired
  DailyExpenseGoalRepository dailyExpenseGoalRepository;

  public ResponseEntity<SavingGoal> createSavingGoal(SavingGoal savingGoal) {
    Wallet lastWallet = walletRepository.getLastAddedWallet();
    if (lastWallet.getMoneyNow().compareTo(savingGoal.getTarget()) == -1) {
      return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }

    BigDecimal walletMoney = lastWallet.getMoneyNow();
    Long days = Duration.between(savingGoal.getStartDate().toLocalDateTime(), savingGoal.getEndDate().toLocalDateTime())
        .toDays();
    savingGoalRepository.save(savingGoal);
    for (int i = 0; i < days; i++) {
      createDailyGoalsforSavingGoal(savingGoal, i);
    }

    List<DailyExpenseGoal> dailyGoals = dailyExpenseGoalRepository.findBySavingGoalId(savingGoal);
    savingGoal.setDailyGoals(dailyGoals);
    return new ResponseEntity(savingGoalRepository.save(savingGoal), HttpStatus.OK);

  }

  private void createDailyGoalsforSavingGoal(SavingGoal savingGoal, int day) {
    DailyExpenseGoal dailyExpenseGoal = new DailyExpenseGoal();
    dailyExpenseGoal.setRegardingDay(savingGoal.getStartDate().toLocalDateTime().toLocalDate().plusDays(day));
    dailyExpenseGoal.setMoneyGoal(BigDecimal.TEN);
    dailyExpenseGoal.setSavingGoal(savingGoal);
    dailyExpenseGoalRepository.save(dailyExpenseGoal);
  }
}
