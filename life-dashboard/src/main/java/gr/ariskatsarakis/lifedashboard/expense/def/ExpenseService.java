package gr.ariskatsarakis.lifedashboard.expense.def;


import java.util.List;

public interface ExpenseService {

    public List<Expense> findAll();

    public Expense addExpense(Expense expense);
    /**
     * Todo
     *
     */
    //findExpensesByDate
    //findExpensesByYear
    //findExpensesByMonth
    //findExpensesByWeek
    //findExpensesByDay
}
