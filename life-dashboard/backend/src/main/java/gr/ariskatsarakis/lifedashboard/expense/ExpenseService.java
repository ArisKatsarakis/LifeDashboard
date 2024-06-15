package gr.ariskatsarakis.lifedashboard.expense;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * ExpenseService
 */
@Service
public class ExpenseService {
  private Logger logger = LoggerFactory.getLogger(ExpenseService.class);
  @Autowired
  ExpenseRepository expenseRepository;

  @Autowired
  ExpenseTypeRepository expenseTypeRepository;

  public List<Expense> getExpense() {
    return expenseRepository.findAll();
  }

  public Expense addExpense(Expense e) {
    return expenseRepository.save(e);
  }

  public Expense updateExpense(Expense e) {
    try {
      Optional<Expense> opt = expenseRepository.findById(e.getExpenseId());
      if (opt.isEmpty()) {
        return null;
      }
      Expense e2 = opt.get();
      e2.setTimestamp(e.getTimestamp());
      e2.setMoney(e.getMoney());
      e2 = expenseRepository.save(e2);
      return e2;
    } catch (Exception exc) {
      logger.info(exc.getMessage());
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, exc.getMessage());
    }
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

  public List<ExpenseType> getExpenseTypes() {

  }

}
