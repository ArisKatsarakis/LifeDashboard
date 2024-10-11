package gr.ariskatsarakis.lifedashboard.goal;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * SavingGoal
 */
@Entity

public class SavingGoal {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long savingGoalId;
  private BigDecimal target;
  private Timestamp startDate;
  private Timestamp endDate;

  public SavingGoal() {
  }

  public void setSavingGoalId(Long savingGoalId) {
    this.savingGoalId = savingGoalId;
  }

  public Long getSavingGoalId() {
    return savingGoalId;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public Timestamp getStartDate() {
    return startDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }

  public Timestamp getEndDate() {
    return endDate;
  }

  public void setTarget(BigDecimal target) {
    this.target = target;
  }

  public BigDecimal getTarget() {
    return target;
  }

  @Override
  public String toString() {
    return "SavingGoal [savingGoalId=" + savingGoalId + ", target=" + target + ", startDate=" + startDate + ", endDate="
        + endDate + "]";
  }

}
