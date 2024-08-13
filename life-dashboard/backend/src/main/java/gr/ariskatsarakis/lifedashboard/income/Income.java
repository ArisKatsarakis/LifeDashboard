package gr.ariskatsarakis.lifedashboard.income;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Income
 */
@Entity
public class Income {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long incomeId;
  private BigDecimal money;
  private Timestamp timestamp;
  private IncomeType incomeType;

  public Income() {
  }

  public void setIncomeType(IncomeType incomeType) {
    this.incomeType = incomeType;
  }

  public IncomeType getIncomeType() {
    return incomeType;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public void setIncomeId(Long incomeId) {
    this.incomeId = incomeId;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public Long getIncomeId() {
    return incomeId;
  }

  public BigDecimal getMoney() {
    return money;
  }

  @Override
  public String toString() {
    return "{ \"money\":  " + this.money + ",\n\"incomeType\": \"" + this.incomeType + "\"}";
  }
}
