package gr.ariskatsarakis.lifedashboard.budget.impl;

import gr.ariskatsarakis.lifedashboard.budget.def.Entry;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import gr.ariskatsarakis.lifedashboard.income.def.Income;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalTime;

@Component
public class EntryMapper  {

    public static final String INCOME_TYPE_STRING = "INCOME";
    public static final String EXPENSE_TYPE_STRING = "EXPENSE";

    public static Entry incomeToEntry(Income income) {
        Entry entry = new Entry();
        entry.setEntryType(INCOME_TYPE_STRING);
        entry.setDateInserted(Timestamp.valueOf(income.getDateCreated().atTime(LocalTime.now())));
        entry.setMoney(income.getMoneyReceived());
        entry.setIncomeId(income.getIncomeId());
        return entry;
    }
    public static Entry expenseToEntry(Expense expense) {
        Entry entry = new Entry();
        entry.setEntryType(EXPENSE_TYPE_STRING);
        entry.setDateInserted(Timestamp.valueOf(expense.getDateCreated().atTime(LocalTime.now())));
        entry.setMoney(expense.getMoneySpent());
        entry.setExpenseId(expense.getExpenseId());
        return entry;
    }
}
