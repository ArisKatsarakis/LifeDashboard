package gr.ariskatsarakis.lifedashboard.budget.impl;

import gr.ariskatsarakis.lifedashboard.budget.def.Budget;
import gr.ariskatsarakis.lifedashboard.budget.def.Entry;
import gr.ariskatsarakis.lifedashboard.budget.def.EntryRepository;
import gr.ariskatsarakis.lifedashboard.samples.BudgetSamples;
import gr.ariskatsarakis.lifedashboard.samples.EntrySamples;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TestBudgetUtilitiesImpl {

    private BudgetUtilitiesImpl undertest = new BudgetUtilitiesImpl();

    @Test
    public void test_createInitialBudget() {
        undertest.entryRepository = mock(EntryRepository.class);
        doReturn(EntrySamples.Sample_Entries()).when(undertest.entryRepository).findAll();
        Budget budget = undertest.createInitialBudget();
        assertEquals(BigDecimal.valueOf(-8l), budget.getWalletMoney());
        assertEquals(budget.getLastExpenseDate(), budget.getLastExpenseDate());
    }


    @Test
    public void test_createBudgetWithExpense() {
        Entry expense = EntrySamples.
                createRandomExpenseEntry();
        Budget lastBudget = BudgetSamples.sampleBudget();
        Budget budgetCreated = undertest.createBudgetFromLastEntry(lastBudget, expense);
        BigDecimal expectedWalletMoney = lastBudget.getWalletMoney().subtract(expense.getMoney());
        assertEquals(expectedWalletMoney, budgetCreated.getWalletMoney());
        assertEquals(expense.getDateInserted(), budgetCreated.getLastExpenseDate());

    }

    @Test
    public void test_createBudget_FromIncome() {
        Entry income = EntrySamples.
                createRandomExpenseEntry();
        Budget lastBudget = BudgetSamples.sampleBudget();
        Budget budgetCreated = undertest.createBudgetFromLastEntry(lastBudget, income);
        BigDecimal expectedWalletMoney = lastBudget.getWalletMoney().add(income.getMoney());
        assertEquals(expectedWalletMoney, budgetCreated.getWalletMoney());
        assertEquals(income.getDateInserted(), budgetCreated.getLastIncomeDate());

    }


}
