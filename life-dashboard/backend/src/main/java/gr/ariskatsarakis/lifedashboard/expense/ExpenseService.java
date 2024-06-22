package gr.ariskatsarakis.lifedashboard.expense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public Expense saveExpenseAddToExpenseType(Long expenseTypeId, Expense expense) {
    Expense saved = expenseRepository.save(expense);
    try {
      Optional<ExpenseType> optional = expenseTypeRepository.findById(expenseTypeId);
      if (optional.isPresent()) {

        ExpenseType type = optional.get();
        List<Expense> expenses = type.getExpense();
        expenses.add(saved);
        type.setExpense(expenses);
        BigDecimal sum = BigDecimal.ZERO;
        for (Expense exp : expenses) {
          sum = sum.add(exp.getMoney());
        }
        type.setExpensesSum(sum);
        expenseTypeRepository.save(type);

        logger.info("Expense added {} to expense type in {}", expense.getName(), type.getExpenseTypeName());
        logger.info("SUM: {} Euros", type.getExpensesSum());

      } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense Type Not found");

      }
    } catch (Exception e) {
      throw e;
    }
    return saved;
  }
}
