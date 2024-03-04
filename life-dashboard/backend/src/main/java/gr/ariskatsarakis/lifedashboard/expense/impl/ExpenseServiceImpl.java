package gr.ariskatsarakis.lifedashboard.expense.impl;

import gr.ariskatsarakis.lifedashboard.budget.def.Entry;
import gr.ariskatsarakis.lifedashboard.budget.def.EntryService;
import gr.ariskatsarakis.lifedashboard.budget.impl.EntryMapper;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseSpecifications;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpensesByTypeDTO;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseRepository;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import gr.ariskatsarakis.lifedashboard.expense.beans.ExpensesByTypeDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
  @Autowired
  private ExpenseRepository expenseRepository;

  @Autowired
  private EntryService entryService;

  @Override
  public List<Expense> findAll() {
    return expenseRepository.findAll();
  }

  @Override
  public Expense addExpense(Expense expense) {
    Expense savedExpense = expenseRepository.save(expense);
    Entry entry = EntryMapper.expenseToEntry(savedExpense);
    entryService.addNewEntry(entry);
    return savedExpense;
  }

  public List<Expense> getExpensesByMonth(int month) {
    return expenseRepository.findForMonth(month);
  }

  @Override
  public ExpensesByTypeDTO getExpensesByType(String expenseType) {
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
    List<Expense> sameTypeExpenses = expenseRepository.findAll(spec);
    ExpensesByTypeDTO expensesByTypeDTO = new ExpensesByTypeDTO(sameTypeExpenses, expenseTypeAsked);
    return expensesByTypeDTO;
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
    if (expenseFromDb.isPresent()) {
      expense.setExpenseId(expenseFromDb.get().getExpenseId());
      Entry entry = EntryMapper.expenseToEntry(expense);
      entryService.updateEntry(entry);
      expenseRepository.save(expense);
    }
    return expense;
  }

  @Override
  public void deleteExpenseById(long expenseId) {
    entryService.deleteEntryByExpenseId(expenseId);
    expenseRepository.deleteExpenseById(expenseId);
  }

  @Override
  public List<Expense> getLast10(Pageable lastTen) {
    return expenseRepository.getLast10();
  }

  @Override
  public List<Expense> getExpenseByMaxMoneySpent(BigDecimal maxMoneySpent) {
    Specification<Expense> specMaxMoneySpent = ExpenseSpecifications.byMmaxMoneySpent(maxMoneySpent);
    return expenseRepository.findAll(specMaxMoneySpent);
  }

  public List<Expense> getExpensesForSpecificDay(LocalDate specificDay) {
    Specification<Expense> specMonth = ExpenseSpecifications.bySpecificDate(specificDay);
    return expenseRepository.findAll(specMonth);
  }
}
