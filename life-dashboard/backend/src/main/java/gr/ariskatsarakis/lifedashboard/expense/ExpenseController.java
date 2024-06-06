package gr.ariskatsarakis.lifedashboard.expense;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

  @Autowired
  private ExpenseService service;

  @GetMapping
  public List<Expense> getExpense() {
    Expense e = new Expense();
    e.setMoney(BigDecimal.TEN);
    e.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    e.setExpenseId(1L);
    service.addExpense(e);
    return service.getExpense();
  }

}
