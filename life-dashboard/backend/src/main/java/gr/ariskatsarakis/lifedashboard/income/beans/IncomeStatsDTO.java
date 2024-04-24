package gr.ariskatsarakis.lifedashboard.income.beans;

import java.math.BigDecimal;
import java.util.List;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeType;

public class IncomeStatsDTO {
  private BigDecimal moneySum;
  private List<Income> incomes;
  private IncomeSource incomeSource;
  private IncomeType incomeType;

  public IncomeStatsDTO() {

  }

  public List<Income> getIncomes() {
    return incomes;
  }

  public void setIncomes(List<Income> incomes) {
    this.incomes = incomes;
  }

  public BigDecimal getMoneySum() {
    return moneySum;
  }

  public void setMoneySum(BigDecimal moneySum) {
    this.moneySum = moneySum;
  }

  public IncomeSource getIncomeSource() {
    return incomeSource;
  }

  public void setIncomeSource(IncomeSource incomeSource) {
    this.incomeSource = incomeSource;
  }

  public IncomeType getIncomeType() {
    return incomeType;
  }

  public void setIncomeType(IncomeType incomeType) {
    this.incomeType = incomeType;
  }

}
