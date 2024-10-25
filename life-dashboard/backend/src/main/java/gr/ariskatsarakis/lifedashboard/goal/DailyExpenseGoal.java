package gr.ariskatsarakis.lifedashboard.goal;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * DailyExpenseGoal
 */
@Entity
public class DailyExpenseGoal {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long dailyExpenseGoalId;
  private Timestamp regardingDay;
  private DailyExpenseGoalStatus status;
  private BigDecimal moneyGoal;

  @ManyToOne
  @JsonIgnore
  SavingGoal savingGoal;

  public void setDailyExpenseGoalId(Long dailyExpenseGoalId) {
    this.dailyExpenseGoalId = dailyExpenseGoalId;
  }

  public Long getDailyExpenseGoalId() {
    return dailyExpenseGoalId;
  }

  public void setStatus(DailyExpenseGoalStatus status) {
    this.status = status;
  }

  public DailyExpenseGoalStatus getStatus() {
    return status;
  }

  public void setMoneyGoal(BigDecimal moneyGoal) {
    this.moneyGoal = moneyGoal;
  }

  public BigDecimal getMoneyGoal() {
    return moneyGoal;
  }

  public void setSavingGoal(SavingGoal savingGoal) {
    this.savingGoal = savingGoal;
  }

  public SavingGoal getSavingGoal() {
    return savingGoal;
  }

  public Timestamp getRegardingDay() {
    return regardingDay;
  }

  public void setRegardingDay(Timestamp regardingDay) {
    this.regardingDay = regardingDay;
  }

  @Override
  public String toString() {
    return "DailyExpenseGoal [dailyExpenseGoalId=" + dailyExpenseGoalId + ", regardingDay=" + regardingDay + ", status="
        + status + ", moneyGoal=" + moneyGoal + "]";
  }
}
