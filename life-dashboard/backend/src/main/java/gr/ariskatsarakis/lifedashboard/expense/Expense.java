package gr.ariskatsarakis.lifedashboard.expense;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Expense
 */
@Entity
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long expenseId;
  private BigDecimal money;
  private Timestamp timestamp;

  public Expense() {

  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public long getExpenseId() {
    return expenseId;
  }

  public void setExpenseId(long expenseId) {
    this.expenseId = expenseId;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

}
