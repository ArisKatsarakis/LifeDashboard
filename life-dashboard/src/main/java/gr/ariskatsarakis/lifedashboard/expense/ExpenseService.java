package gr.ariskatsarakis.lifedashboard.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    List<Expense> returnAll() {
            return expenseRepository.findAll();
    }
}
