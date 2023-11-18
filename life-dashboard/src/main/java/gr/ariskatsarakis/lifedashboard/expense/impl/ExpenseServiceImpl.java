package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseSpecifications;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseRepository;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public List<Expense> getExpensesByMonth(int month) {
        return expenseRepository.findForMonth(month);
    }
    @Override
    public List<Expense> getExpensesByType(String expenseType) {
        ExpenseType expenseTypeAsked = null;
        switch (expenseType) {
            case "savings":
                expenseTypeAsked = ExpenseType.SAVINGS;
                break;
            case "fun":
                expenseTypeAsked = ExpenseType.FUN;
                break;
            case "debts":
                expenseTypeAsked = ExpenseType.DEBTS;
                break;
            case "utilities":
                expenseTypeAsked = ExpenseType.UTILITIES;
                break;
        }
        Specification<Expense> spec = ExpenseSpecifications.byExpenseType(expenseTypeAsked);
        return expenseRepository.findAll(spec);
    }

    @Override
    public List<ExpenseType> getExpenseTypes() {
        List<ExpenseType> expenseTypes = Arrays.asList(ExpenseType.values());
        return expenseTypes;
    }

    @Override
    public Expense getExpenseById(long expenseId) {
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        if (expense.isPresent()) {
            return expense.get();
        }
        return null;
    }

    @Override
    public Expense updateExpenseById(long expenseId, Expense expense) {
        Optional<Expense> expenseFromDb = expenseRepository.findById(expenseId);
        if(expenseFromDb.isPresent()) {
            expense.setExpenseId(expenseFromDb.get().getExpenseId());
            expenseRepository.save(expense);
        }
        return expense;
    }

    public List<Expense> getExpensesForSpecificDay(LocalDate specificDay) {
        Specification<Expense> specMonth = ExpenseSpecifications.bySpecificDate(specificDay);
        return expenseRepository.findAll(specMonth);
    }
}
