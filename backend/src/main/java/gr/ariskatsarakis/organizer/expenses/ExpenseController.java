package gr.ariskatsarakis.organizer.expenses;

import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExpenseController
 */
@RestController
@RequestMapping("api/v1/expenses")
@CrossOrigin
public class ExpenseController {

  private ExpenseService expenseService;

  public ExpenseController(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @PostMapping
  public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expense)
      throws Exception {
    if (expense.getDateCreated() == null) {
      throw new Exception();
    }

    ExpenseDTO dto =
        expenseService.addExpense(expenseService.fromDTOToExpense(expense));
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/count")
  public ResponseEntity<Integer> getExpensesCount() {
    return expenseService.getExpensesCountByUser();
  }

  @GetMapping
  public ResponseEntity<List<ExpenseDTO>>
  getExpenses(@PathParam("limit") Integer limit,
              @PathParam("page") Integer page) {
    List<ExpenseDTO> expenses = expenseService.toListExpenseDtos(
        expenseService.fetchExpenses(limit, page));
    return new ResponseEntity<>(expenses, HttpStatus.OK);
  }

  @GetMapping("/categories")
  public ResponseEntity<ExpenseCategory[]> getExpenseCategories() {
    return new ResponseEntity<>(ExpenseCategory.values(), HttpStatus.OK);
  }

  @GetMapping("/categories/{category}")
  public ResponseEntity<List<ExpenseDTO>>
  getExpensesByCategory(@PathVariable ExpenseCategory category) {
    return new ResponseEntity<>(
        expenseService.fetchExpensesByCategories(category), HttpStatus.OK);
  }
}
