package gr.ariskatsarakis.lifedashboard.wallet;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Wallet
 */
@Entity
public class Wallet {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long walletId;
  private BigDecimal moneyNow;
  private Timestamp lastExpenseDate;
  private Timestamp lastIncomeDate;
  private Timestamp dateCreated;

  public Wallet() {

  }

  public void setWalletId(Long walletId) {
    this.walletId = walletId;
  }

  public Long getWalletId() {
    return walletId;
  }

  public void setMoneyNow(BigDecimal moneyNow) {
    this.moneyNow = moneyNow;
  }

  public BigDecimal getMoneyNow() {
    return moneyNow;
  }

  public void setLastExpenseDate(Timestamp lastExpenseDate) {
    this.lastExpenseDate = lastExpenseDate;
  }

  public Timestamp getLastIncomeDate() {
    return lastIncomeDate;
  }

  public void setLastIncomeDate(Timestamp lastIncomeDate) {
    this.lastIncomeDate = lastIncomeDate;
  }

  public Timestamp getLastExpenseDate() {
    return lastExpenseDate;
  }

  public void setDateCreated(Timestamp dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Timestamp getDateCreated() {
    return dateCreated;
  }

  @Override
  public String toString() {
    return "";
  }
}
