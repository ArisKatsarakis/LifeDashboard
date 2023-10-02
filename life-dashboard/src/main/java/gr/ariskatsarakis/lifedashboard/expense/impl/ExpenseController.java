package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseCritria;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping
;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/api/v1/expenses")
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

    @PostMapping("api/v1/expenses/criteria/")
    List<Expense> getExpensesUsingCriteria(@RequestBody ExpenseCritria criteria) {

        return expenseService.getExpensesUsingCriteria(criteria);

    }



}
