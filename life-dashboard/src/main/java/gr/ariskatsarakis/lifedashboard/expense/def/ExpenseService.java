package gr.ariskatsarakis.lifedashboard.expense.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExpenseService {

    public List<Expense> findAll();

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
