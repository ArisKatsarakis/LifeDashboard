package gr.ariskatsarakis.lifedashboard.expense.def;


import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {

     List<Expense> findAll();

     Expense addExpense(Expense expense);

    List<Expense> getExpensesByMonth(int month);

    List<Expense> getExpensesForSpecificDay(LocalDate specificDay);

    List<Expense> getExpensesByType(String expenseType);

    List<ExpenseType> getExpenseTypes();

    Expense getExpenseById(long expenseId);

    Expense updateExpenseById(long expenseId, Expense expense);

    void deleteExpenseById(long expenseId);

    List<Expense> getLast10(Pageable lastTen);
}
