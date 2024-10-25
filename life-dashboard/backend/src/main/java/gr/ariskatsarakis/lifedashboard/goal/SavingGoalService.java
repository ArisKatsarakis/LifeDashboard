package gr.ariskatsarakis.lifedashboard.goal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.expense.Expense;
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

  Logger logger = LoggerFactory.getLogger(this.getClass());

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
    dailySpendingTarget = dailySpendingTarget.divide(BigDecimal.valueOf(days), RoundingMode.HALF_DOWN);
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

  public void recalculateDailySavingGoal(Wallet wallet) {
    List<DailyExpenseGoal> dEGoalsToBeUpdated = dailyExpenseGoalRepository.findAll().stream()
        .filter(dEGoal -> dEGoal.getRegardingDay().compareTo(wallet.getDateCreated()) == 0
            || dEGoal.getRegardingDay().compareTo(wallet.getDateCreated()) == 1)
        .collect(Collectors.toList());
  }

  public void updateDailyExpenseGoalwithExpense(Expense expense) {
    List<DailyExpenseGoal> dEGoalsToBeUpdated = dailyExpenseGoalRepository.findAll().stream()
        .filter(
            dEGoal -> dEGoal.getRegardingDay().toLocalDateTime().toLocalDate()
                .compareTo(expense.getTimestamp().toLocalDateTime().toLocalDate()) == 0)
        .collect(Collectors.toList());
    for (DailyExpenseGoal dailyExpenseGoal : dEGoalsToBeUpdated) {
      BigDecimal newValue = dailyExpenseGoal.getMoneyGoal().subtract(expense.getMoney());
      dailyExpenseGoal.setMoneyGoal(newValue);
      dailyExpenseGoalRepository.save(dailyExpenseGoal);
    }

  }

  private void createDailyGoalsforSavingGoal(SavingGoal savingGoal, int day, BigDecimal moneyGoal) {
    DailyExpenseGoal dailyExpenseGoal = new DailyExpenseGoal();
    dailyExpenseGoal.setRegardingDay(Timestamp.valueOf(savingGoal.getStartDate().toLocalDateTime().plusDays(day)));
    dailyExpenseGoal.setMoneyGoal(moneyGoal);
    dailyExpenseGoal.setSavingGoal(savingGoal);
    dailyExpenseGoal.setStatus(DailyExpenseGoalStatus.PENDING);
    dailyExpenseGoalRepository.save(dailyExpenseGoal);

  }

  public ResponseEntity<List<SavingGoal>> findAllSavingGoals() {
    ResponseEntity<List<SavingGoal>> savingGoals = new ResponseEntity<>(savingGoalRepository.findAll(), HttpStatus.OK);
    return savingGoals;
  }
}
