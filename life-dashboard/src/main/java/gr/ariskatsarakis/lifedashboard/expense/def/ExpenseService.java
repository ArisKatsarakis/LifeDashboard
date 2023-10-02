package gr.ariskatsarakis.lifedashboard.expense.def;


import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseCritria;

import java.util.List;

public interface ExpenseService {

    public List<Expense> findAll();

    public Expense addExpense(Expense expense);

    List<Expense> getExpensesUsingCriteria(ExpenseCritria criteria);

}
