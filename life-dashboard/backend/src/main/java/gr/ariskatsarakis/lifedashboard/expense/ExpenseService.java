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

  public void addExpense(Expense e) {
    expenses.add(e);
  }

  public void updateExpense(Expense e) {
    expenses.forEach(
        expense -> {
          if (expense.getExpenseId() == e.getExpenseId()) {
            expense.setTimestamp(e.getTimestamp() != null ? e.getTimestamp() : expense.getTimestamp());
            expense.setMoney(e.getMoney() != null ? e.getMoney() : expense.getMoney());
          }
        });
  }

  public Expense getExpenseById(Long expenseId) {
    for (Expense expense : expenses) {
      if (expense.getExpenseId() == expenseId) {
        return expense;

      }
    }
    return null;
  }

}
