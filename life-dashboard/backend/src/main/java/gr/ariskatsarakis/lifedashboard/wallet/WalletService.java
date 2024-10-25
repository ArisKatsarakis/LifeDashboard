package gr.ariskatsarakis.lifedashboard.wallet;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.expense.Expense;
import gr.ariskatsarakis.lifedashboard.goal.SavingGoalService;
import gr.ariskatsarakis.lifedashboard.income.Income;

/**
 * WalletService
 * TODO fix return only ResponseEntity
 */
@Service
public class WalletService {

  @Autowired
  WalletRepository walletRepository;

  @Autowired
  SavingGoalService savingGoalService;

  public ResponseEntity<List<Wallet>> getAll() {
    return new ResponseEntity(walletRepository.findAll(), HttpStatus.OK);
  }

  public Wallet addNewExpenseAtWallet(Expense e) {
    if (walletRepository.findAll().isEmpty()) {
      return walletRepository.save(createFirstWallet(e));
    }
    Wallet wallet = new Wallet();
    Wallet lastWallet = walletRepository.getLastAddedWallet();
    wallet.setMoneyNow(lastWallet.getMoneyNow().subtract(e.getMoney()));
    wallet.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
    wallet.setLastIncomeDate(e.getTimestamp());
    wallet.setLastIncomeDate(lastWallet.getLastExpenseDate());
    Wallet updateWallet = walletRepository.save(wallet);
    savingGoalService.updateDailyExpenseGoalwithExpense(e);
    return updateWallet;
  }

  public Wallet addNewIncomeAtWallet(Income i) {
    if (walletRepository.findAll().isEmpty()) {
      return walletRepository.save(createFirstWallet(i));
    }
    Wallet wallet = new Wallet();
    Wallet lastWallet = walletRepository.getLastAddedWallet();
    wallet.setMoneyNow(lastWallet.getMoneyNow().add(i.getMoney()));
    wallet.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
    wallet.setLastIncomeDate(i.getTimestamp());
    wallet.setLastExpenseDate(lastWallet.getLastExpenseDate());
    Wallet updateWallet = walletRepository.save(wallet);
    savingGoalService.recalculateDailySavingGoal(updateWallet);
    return updateWallet;
  }

  Wallet createFirstWallet(Object entry) {
    Wallet wallet = new Wallet();
    wallet.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
    wallet.setMoneyNow(BigDecimal.ZERO);
    if (entry instanceof Expense) {
      Expense e = (Expense) entry;
      wallet.setLastExpenseDate(e.getTimestamp());
      wallet.setMoneyNow(wallet.getMoneyNow().subtract(e.getMoney()));
    } else if (entry instanceof Income) {
      Income i = (Income) entry;
      wallet.setLastIncomeDate(i.getTimestamp());
      wallet.setMoneyNow(wallet.getMoneyNow().add(i.getMoney()));
    }
    return wallet;
  }

  public ResponseEntity<Wallet> getLastWallet() {
    Wallet wallet = walletRepository.getLastAddedWallet();
    return new ResponseEntity<Wallet>(wallet, wallet == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }
}
