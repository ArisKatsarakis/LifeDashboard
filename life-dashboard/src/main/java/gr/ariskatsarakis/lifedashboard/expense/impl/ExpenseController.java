package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class ExpenseController {


    @Autowired
    private ExpenseService expenseService;
    @GetMapping("hello-world")
    String HelloWorld(){
        String message = "Hello World";
        return message;
    }

    @GetMapping("/api/v1/expenses")
    List<Expense> returnAllExpenses() {
            return expenseService.findAll();
    }
}
