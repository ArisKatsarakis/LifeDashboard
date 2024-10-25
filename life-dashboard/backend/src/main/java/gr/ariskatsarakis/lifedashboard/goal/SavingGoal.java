package gr.ariskatsarakis.lifedashboard.goal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

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

  @OneToMany
  @JoinColumn(name = "savingGoal")
  List<DailyExpenseGoal> dailyGoals;

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

  public void setDailyGoals(List<DailyExpenseGoal> dailyGoals) {
    this.dailyGoals = dailyGoals;
  }

  public List<DailyExpenseGoal> getDailyGoals() {
    return dailyGoals;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
    return new StringBuilder()
        .append("{ ")
        .append("\"target\":" + target + ",")
        .append("\"startDate\":\"" + startDate.toLocalDateTime().format(formatter) + "\",")
        .append("\"endDate\":\"" + endDate.toLocalDateTime().format(formatter) + "\"")
        .append(" }")
        .toString();
  }

}
