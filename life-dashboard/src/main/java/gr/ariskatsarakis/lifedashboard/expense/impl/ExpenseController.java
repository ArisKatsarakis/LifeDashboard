package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ExpenseController {

    private final Logger controllerLogger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/api/v1/expenses")
    @CrossOrigin
    List<Expense> returnAllExpenses() {
            return expenseService.findAll();
    }

    @PostMapping("/api/v1/expenses")
    @CrossOrigin
    Expense createNewExpense(@RequestBody Expense expense) {
        if(expense.getExpenseId() == 0L) {
            return expenseService.addExpense(expense);
        }
        return expense;
        
    }

    @GetMapping("api/v1/expenses/type")
    List<Expense> getExpensesByType(@RequestParam String expenseType) {
        return expenseService.getExpensesByType(expenseType);
    }

    @GetMapping("api/v1/expenses/criteria/{month}")
    List<Expense> getExpensesByMonth(@PathVariable int month) {
        return expenseService.getExpensesByMonth(month);
    }


    @GetMapping("api/v1/expenses/{expenseId}")
    @CrossOrigin
    Expense getExpenseById(@PathVariable long expenseId) {
        return expenseService.getExpenseById(expenseId);
    }
    @GetMapping("api/v1/expenses/criteria")
    List<Expense> getExpensesBySpecificDay(@RequestParam LocalDate specificDay) {
        controllerLogger.info("Expenses for : "+ specificDay.toString()  + " are requested.");
        return expenseService.getExpensesForSpecificDay(specificDay);
    }

    @PutMapping("api/v1/expenses/{expenseId}")
    @CrossOrigin
    Expense updateExpenseById(@PathVariable long expenseId, @RequestBody Expense expense) {
        return expenseService.updateExpenseById(expenseId, expense);
    }

    @GetMapping("api/v1/expense-types")
    @CrossOrigin
    List<ExpenseType> getExpenseTypes() {
        return expenseService.getExpenseTypes();
    }

    @DeleteMapping("api/v1/expenses/{expenseId}")
    @CrossOrigin
    public void deleteExpenseById(@PathVariable long expenseId) {


        expenseService.deleteExpenseById(expenseId);
    }

    @GetMapping("api/v1/expenses-last-10")
    @CrossOrigin
    public List<Expense> getLast10Expenses() {
        Pageable lastTen = PageRequest.of(0, 10);

        return expenseService.getLast10(lastTen);
    }

    //TODO Implement Get Using Criteria
}

