package gr.ariskatsarakis.lifedashboard.wallet;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.expense.Expense;
import gr.ariskatsarakis.lifedashboard.income.Income;

/**
 * WalletService
 */
@Service
public class WalletService {

  @Autowired
  WalletRepository walletRepository;

  public Wallet addNewExpenseAtWallet(Expense e) {
    // substract money or create new wallet with minus
    // return the new wallet
    if (walletRepository.findAll().isEmpty()) {
      return createFirstWallet(e);
    }
    return null;
  }

  public Wallet addNewIncomeAtWallet(Income i) {
    // add money or create a new wallet
    // return the new wallet
    return null;
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
}
