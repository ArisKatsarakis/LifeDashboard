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
    if (lastWallet == null) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    if (lastWallet.getMoneyNow().compareTo(savingGoal.getTarget()) == -1) {
      return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }

    BigDecimal walletMoney = lastWallet.getMoneyNow();
    Long days = Duration.between(savingGoal.getStartDate().toLocalDateTime(), savingGoal.getEndDate().toLocalDateTime())
        .toDays();
    savingGoalRepository.save(savingGoal);
    BigDecimal dailySpendingTarget = walletMoney.subtract(savingGoal.getTarget());
    dailySpendingTarget = dailySpendingTarget.divide(BigDecimal.valueOf(days));
    for (int i = 0; i < days; i++) {
      createDailyGoalsforSavingGoal(savingGoal, i, dailySpendingTarget);
    }

    List<DailyExpenseGoal> dailyGoals = dailyExpenseGoalRepository.findBySavingGoalId(savingGoal);
    savingGoal.setDailyGoals(dailyGoals);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    ResponseEntity<SavingGoal> responseOfService = new ResponseEntity(savingGoalRepository.save(savingGoal),
        HttpStatus.OK);
    return responseOfService;

  }

  private void createDailyGoalsforSavingGoal(SavingGoal savingGoal, int day, BigDecimal moneyGoal) {
    DailyExpenseGoal dailyExpenseGoal = new DailyExpenseGoal();
    dailyExpenseGoal.setRegardingDay(savingGoal.getStartDate().toLocalDateTime().toLocalDate().plusDays(day));
    dailyExpenseGoal.setMoneyGoal(moneyGoal);
    dailyExpenseGoal.setSavingGoal(savingGoal);
    dailyExpenseGoalRepository.save(dailyExpenseGoal);

  }

  public ResponseEntity<List<SavingGoal>> findAllSavingGoals() {
    ResponseEntity<List<SavingGoal>> savingGoals = new ResponseEntity<>(savingGoalRepository.findAll(), HttpStatus.OK);
    return savingGoals;
  }
}
