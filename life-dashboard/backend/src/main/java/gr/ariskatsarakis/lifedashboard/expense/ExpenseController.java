package gr.ariskatsarakis.lifedashboard.expense;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

  @Autowired
  private ExpenseService service;

  @GetMapping("/expenses")
  public List<Expense> getExpense() {
    return service.getExpense();
  }

  @PostMapping("/expenses")
  public Expense addExpense(@RequestBody Expense expense) {
    expense.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    return service.addExpense(expense);
  }

  @PutMapping("/expense-types/{expenseTypeId}/expenses")
  public Expense updateExpense(@RequestBody Expense expense, @PathVariable Long expenseTypeId) {
    return service.updateExpense(expense, expenseTypeId);
  }

  @GetMapping("/expense-types")
  public List<ExpenseType> getExpenseTypes() {
    return service.getExpenseTypes();
  }

  @GetMapping("/expense-types/{expenseTypeId}")
  public ExpenseType getExpenseTypes(@PathVariable Long expenseTypeId) {
    return service.getExpenseTypesById(expenseTypeId);
  }

  @PostMapping("/expense-types")
  public ExpenseType addExpenseType(@RequestBody ExpenseType expenseType) {
    return service.addExpenseType(expenseType);
  }

  @GetMapping("/expense-types/{expenseTypeId}/expenses")
  public List<Expense> getExpensesByExpenseType(@PathVariable Long expenseTypeId) {
    return service.getExpenseByExpenseTypeId(expenseTypeId);
  }

  @PostMapping("/expense-types/{expenseTypeId}/expenses")
  public Expense addExpeseAndAddToExpnseType(@PathVariable Long expenseTypeId, @RequestBody Expense expense) {
    expense.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    return service.saveExpenseAddToExpenseType(expenseTypeId, expense);
  }

}
