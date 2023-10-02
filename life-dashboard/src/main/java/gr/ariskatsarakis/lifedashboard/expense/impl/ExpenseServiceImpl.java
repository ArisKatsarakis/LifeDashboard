package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseCritria;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseRepository;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;



    @Override
    public List<Expense> findAll() {
            return expenseRepository.findAll();
    }

    @Override
    public Expense addExpense(Expense expense) {

       return  expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getExpensesUsingCriteria(ExpenseCritria criteria) {
      
        return null;
    }
}
