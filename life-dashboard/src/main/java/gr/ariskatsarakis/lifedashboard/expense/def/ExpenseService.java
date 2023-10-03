package gr.ariskatsarakis.lifedashboard.expense.def;


import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseCriteria;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

     List<Expense> findAll();

     Expense addExpense(Expense expense);

    List<Expense> getExpensesByMonth(int month);

    List<Expense> getExpensesForSpecificDay(LocalDate specificDay);

    List<Expense> getExpensesByType(String expenseType);
}
