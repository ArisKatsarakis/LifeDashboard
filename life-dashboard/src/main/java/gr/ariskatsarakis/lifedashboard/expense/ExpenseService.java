package gr.ariskatsarakis.lifedashboard.expense;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExpenseService {

    static Set<Expense> expenses = new HashSet<>();
    static {
        expenses.add(new Expense("expense"));
        expenses.add(new Expense("expense2"));
    }
    Set<Expense> returnAll() {
    return  expenses;
    }
}
