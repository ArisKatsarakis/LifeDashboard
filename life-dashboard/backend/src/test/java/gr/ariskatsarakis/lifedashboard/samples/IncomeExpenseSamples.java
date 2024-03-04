package gr.ariskatsarakis.lifedashboard.samples;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import gr.ariskatsarakis.lifedashboard.income.def.Income;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeType;

public class IncomeExpenseSamples {

  public Income getSampleIncome() {
    Income income = new Income();
    IncomeSource incomeSource = new IncomeSource();
    incomeSource.setName("Name");
    incomeSource.setIncomeType(IncomeType.CODING);
    income.setIncomeId(1L);
    income.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
    income.setDescription("Sample Income");
    income.setMoneyReceived(BigDecimal.TEN);
    income.setIncomeSource(incomeSource);
    return income;
  }

  public Expense getSampleExpense() {
    Expense expense = new Expense();
    expense.setExpenseId(1);
    expense.setDateCreated(LocalDate.now());
    expense.setExpenseType(ExpenseType.FUN);
    expense.setMoneySpent(BigDecimal.TEN);
    return expense;
  }
}
