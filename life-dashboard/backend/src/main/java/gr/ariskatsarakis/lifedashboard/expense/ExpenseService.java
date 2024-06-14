package gr.ariskatsarakis.lifedashboard.expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ExpenseService
 */
@Service
public class ExpenseService {

  @Autowired
  ExpenseRepository expenseRepository;
  public static List<Expense> expenses = new ArrayList<>();

  public List<Expense> getExpense() {
    return expenseRepository.findAll();
  }

  public Expense addExpense(Expense e) {
    return expenseRepository.save(e);
  }

  public Expense updateExpense(Expense e) {
    Optional<Expense> opt = expenseRepository.findById(e.getExpenseId());
    if (opt.isEmpty()) {
      return null;
    }
    Expense e2 = opt.get();
    e2.setTimestamp(e.getTimestamp());
    e2.setMoney(e.getMoney());
    e2 = expenseRepository.save(e2);
    return e2;
  }

  public Expense getExpenseById(Long expenseId) {
    for (Expense expense : expenses) {
      if (expense.getExpenseId() == expenseId) {
        return expense;

      }
    }
    return null;
  }

  public void deleteExpense(Expense e) {
    int index = -1;
    for (Expense expense : expenses) {
      if (expense.getExpenseId() == e.getExpenseId()) {
        index = expenses.indexOf(expense);
      }
    }
    if (index != -1) {
      expenses.remove(index);
    }
  }

}
