package gr.ariskatsarakis.lifedashboard.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
    Set<Expense> returnAllExpenses() {
            return expenseService.returnAll();
    }
}
