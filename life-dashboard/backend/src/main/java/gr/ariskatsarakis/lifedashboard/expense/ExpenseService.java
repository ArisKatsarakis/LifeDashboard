package gr.ariskatsarakis.lifedashboard.expense;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import gr.ariskatsarakis.lifedashboard.income.Income;
import gr.ariskatsarakis.lifedashboard.income.IncomeRepository;

/**
 * ExpenseService
 */
@Service
public class ExpenseService {
  private Logger logger = LoggerFactory.getLogger(ExpenseService.class);
  @Autowired
  ExpenseRepository expenseRepository;

  @Autowired
  IncomeRepository incomeRepository;

  @Autowired
  ExpenseTypeRepository expenseTypeRepository;

  public List<Expense> getExpense() {
    return expenseRepository.findAll();
  }

  public Expense addExpense(Expense e) {
    Expense expenseAdded = expenseRepository.save(e);
    List<Income> incomes = incomeRepository.findAll();
    logger.info("INCOMES SIZE IS : " + incomes.size());

    if (incomes.size() > 0) {
      logger.info("Added new Income ");
      Income income = incomes.get(0);
      BigDecimal currentMoney = income.getMoney().subtract(expenseAdded.getMoney());
      income.setMoney(currentMoney);
      income.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
      incomeRepository.save(income);
    }

    return expenseAdded;
  }

  public Expense updateExpense(Expense e, Long expenseTypeId) {
    try {
      Optional<Expense> opt = expenseRepository.findById(e.getExpenseId());
      if (opt.isEmpty()) {
        return null;
      }

      Expense e2 = opt.get();
      BigDecimal currentMoney = e2.getMoney();
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

      List<Income> incomes = incomeRepository.findAll();
      Income income = incomes.isEmpty() == false ? incomes.get(0) : null;
      if (income != null) {
        income.setMoney(income.getMoney().add(currentMoney.subtract(e2.getMoney())));
      }
      income.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
      incomeRepository.save(income);
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
    Expense saved = addExpense(expense);
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

  public ExpenseType getExpenseTypesById(Long expenseTypeId) {
    Optional<ExpenseType> opt = expenseTypeRepository.findById(expenseTypeId);
    if (opt.isPresent()) {
      return opt.get();
    }
    return null;

  }
}
