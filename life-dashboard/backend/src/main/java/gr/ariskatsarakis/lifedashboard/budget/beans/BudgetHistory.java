package gr.ariskatsarakis.lifedashboard.budget.beans;

import gr.ariskatsarakis.lifedashboard.budget.def.Budget;
import gr.ariskatsarakis.lifedashboard.budget.def.BudgetRepository;
import gr.ariskatsarakis.lifedashboard.budget.def.Entry;

import java.util.List;

/**
 * Class created for representing the history of the last ten entries and the current budget
 */

public class BudgetHistory {

    private Budget budget;
    private List<Entry> tenLastEntries;

    public BudgetHistory() {

    }

    @Override
    public String toString() {
        return "BudgetHistory{" +
                "budget=" + budget +
                ", tenLastEntries=" + tenLastEntries +
                '}';
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public List<Entry> getTenLastEntries() {
        return tenLastEntries;
    }

    public void setTenLastEntries(List<Entry> tenLastEntries) {
        this.tenLastEntries = tenLastEntries;
    }
}
