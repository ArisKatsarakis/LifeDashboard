package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseCriteria;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseSpecifications;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseRepository;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public List<Expense> getExpensesUsingCriteria(ExpenseCriteria criteria) {
        Specification<Expense> spec = ExpenseSpecifications.byExpenseType(ExpenseType.SAVINGS);
        Specification<Expense> specMonth = ExpenseSpecifications.byMonth(
                LocalDate.of(2023,1,1)
        );
        return expenseRepository.findAll(specMonth);
    }
}
