package gr.ariskatsarakis.lifedashboard.expense.def;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpensesByTypeDTO;

import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

  List<Expense> findAll();

  Expense addExpense(Expense expense);

  List<Expense> getExpensesByMonth(int month);

  List<Expense> getExpensesForSpecificDay(LocalDate specificDay);

  ExpensesByTypeDTO getExpensesByType(String expenseType);

  List<Expense> getExpenseByMaxMoneySpent(BigDecimal maxMoneySpent);

  List<ExpenseType> getExpenseTypes();

  Expense getExpenseById(long expenseId);

  Expense updateExpenseById(long expenseId, Expense expense);

  void deleteExpenseById(long expenseId);

  List<Expense> getLast10(Pageable lastTen);
}
