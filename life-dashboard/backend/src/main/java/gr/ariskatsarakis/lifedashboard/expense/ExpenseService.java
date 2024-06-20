package gr.ariskatsarakis.lifedashboard.expense;

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

  public Expense updateExpense(Expense e, Long expenseTypeId) {
    try {
      Optional<Expense> opt = expenseRepository.findById(e.getExpenseId());
      if (opt.isEmpty()) {
        return null;
      }

      Expense e2 = opt.get();
      e2.setTimestamp(e.getTimestamp());
      e2.setMoney(e.getMoney());
      Optional<ExpenseType> expenseType = expenseTypeRepository.findById(expenseTypeId);
      if (!expenseType.isEmpty()) {
        e2.setExpenseType(expenseType.get());
        List<Expense> expenses = expenseType.get().getExpense();
        expenses.add(e2);
        expenseType.get().setExpense(expenses);
        expenseTypeRepository.save(expenseType.get());
      }

      e2 = expenseRepository.save(e2);
      return e2;
    } catch (Exception exc) {
      logger.info(exc.getMessage());
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, exc.getMessage());
    }
  }

  public Expense getExpenseById(Long expenseId) {
    return null;
  }

  public void deleteExpense(Expense e) {
  }

  public List<ExpenseType> getExpenseTypes() {
    return expenseTypeRepository.findAll();
  }

  public ExpenseType addExpenseType(ExpenseType expenseType) {
    return expenseTypeRepository.save(expenseType);
  }

  public List<Expense> getExpenseByExpenseTypeId(Long expenseTypeId) {
    Optional<ExpenseType> optional = expenseTypeRepository.findById(expenseTypeId);
    if (optional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense Type not found");
    }
    return optional.get().getExpense();
  }
}
