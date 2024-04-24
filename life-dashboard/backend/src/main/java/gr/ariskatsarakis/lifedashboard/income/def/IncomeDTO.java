package gr.ariskatsarakis.lifedashboard.income.def;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeDTO {
  private Long incomeId;
  private Long incomeSourceId;
  private String description;
  private LocalDate dateCreated;
  private BigDecimal MoneyReceived;
  private String incomeSourceName;

  public IncomeDTO() {

  }

  public void setMoneyReceived(BigDecimal moneyReceived) {
    MoneyReceived = moneyReceived;
  }

  public BigDecimal getMoneyReceived() {
    return MoneyReceived;
  }

  public void setIncomeSourceName(String incomeSourceName) {
    this.incomeSourceName = incomeSourceName;
  }

  public String getIncomeSourceName() {
    return incomeSourceName;
  }

  public void setIncomeId(Long incomeId) {
    this.incomeId = incomeId;
  }

  public Long getIncomeId() {
    return incomeId;
  }

  public void setIncomeSourceId(Long incomeSourceId) {
    this.incomeSourceId = incomeSourceId;
  }

  public Long getIncomeSourceId() {
    return incomeSourceId;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }
}
