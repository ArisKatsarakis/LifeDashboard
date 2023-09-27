package gr.ariskatsarakis.lifedashboard.expense;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ExpenseController {

    @GetMapping("/api/v1/expenses")
    String HelloWorld(){
        String message = "Hello World";
        return message;
    }
}
