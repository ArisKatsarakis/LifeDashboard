package gr.ariskatsarakis.lifedashboard.expense;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * ExpenseService
 */
@Service
public class ExpenseService {

  public static List<Expense> expenses = new ArrayList<>();

  public List<Expense> getExpense() {
    return expenses;
  }

}
