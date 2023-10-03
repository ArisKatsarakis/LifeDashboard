package gr.ariskatsarakis.lifedashboard.expense.def;


import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseCriteria;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

    public List<Expense> findAll();

    public Expense addExpense(Expense expense);

    List<Expense> getExpensesUsingCriteria(ExpenseCriteria criteria);

    List<Expense> getExpensesByMonth(int month);

    List<Expense> getExpensesForSpecificDay(LocalDate specificDay);
}
