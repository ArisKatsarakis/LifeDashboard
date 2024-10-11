package gr.ariskatsarakis.lifedashboard.goal;

import java.math.BigDecimal;
import java.time.Duration;

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

  public ResponseEntity<SavingGoal> createSavingGoal(SavingGoal savingGoal) {
    Wallet lastWallet = walletRepository.getLastAddedWallet();
    if (lastWallet.getMoneyNow().compareTo(savingGoal.getTarget()) == -1) {
      return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }
    BigDecimal walletMoney = lastWallet.getMoneyNow();
    Long days = Duration.between(savingGoal.getStartDate().toLocalDateTime(), savingGoal.getEndDate().toLocalDateTime())
        .toDays();
    return new ResponseEntity(savingGoalRepository.save(savingGoal), HttpStatus.OK);
  }
}
