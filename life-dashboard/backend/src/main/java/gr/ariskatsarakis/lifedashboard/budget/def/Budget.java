package gr.ariskatsarakis.lifedashboard.budget.def;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "budget")
public class Budget {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long budgetId;

  private Timestamp lastIncomeDate;

  private Timestamp lastExpenseDate;

  /**
   * Money right now
   */
  private BigDecimal walletMoney;

  private Timestamp dateCreated;

  public Budget() {
  }

  public Budget(long budgetId, Timestamp lastIncomeDate, Timestamp lastExpenseDate, BigDecimal walletMoney,
      Timestamp dateCreated) {
    this.budgetId = budgetId;
    this.lastIncomeDate = lastIncomeDate;
    this.lastExpenseDate = lastExpenseDate;
    this.walletMoney = walletMoney;
    this.dateCreated = dateCreated;
  }

  public long getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(long budgetId) {
    this.budgetId = budgetId;
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

  public void setLastExpenseDate(Timestamp lastExpenseDate) {
    this.lastExpenseDate = lastExpenseDate;
  }

  public BigDecimal getWalletMoney() {
    return walletMoney;
  }

  public void setWalletMoney(BigDecimal walletMoney) {
    this.walletMoney = walletMoney;
  }

  public Timestamp getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Timestamp dateCreated) {
    this.dateCreated = dateCreated;
  }

}
