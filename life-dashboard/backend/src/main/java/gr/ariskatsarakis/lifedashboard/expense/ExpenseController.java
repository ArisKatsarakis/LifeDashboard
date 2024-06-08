package gr.ariskatsarakis.lifedashboard.expense;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

  @Autowired
  private ExpenseService service;

  @GetMapping
  public List<Expense> getExpense() {
    return service.getExpense();
  }

  @PostMapping
  public Expense addExpense(@RequestBody Expense expense) {
    expense.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    return service.addExpense(expense);
  }

}
