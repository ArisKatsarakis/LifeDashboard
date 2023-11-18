package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("api/v1/expenses/criteria")
    List<Expense> getExpensesBySpecificDay(@RequestParam LocalDate specificDay) {
        controllerLogger.info("Expenses for : "+ specificDay.toString()  + " are requested.");
        return expenseService.getExpensesForSpecificDay(specificDay);
    }

    @GetMapping("api/v1/expense-types")
    List<ExpenseType> getExpenseTypes() {
        return expenseService.getExpenseTypes();
    }
}
