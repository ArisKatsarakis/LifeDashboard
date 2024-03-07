package gr.ariskatsarakis.lifedashboard.income.beans;

import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeType;
import gr.ariskatsarakis.lifedashboard.income.def.Income; 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class IncomeStatsDTO {
  private BigDecimal moneySum;
  private List<Income> incomes;
  private IncomeSource incomeSource;
  private IncomeType incomeType;
}
