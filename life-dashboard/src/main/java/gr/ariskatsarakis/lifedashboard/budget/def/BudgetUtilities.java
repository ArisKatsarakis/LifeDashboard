package gr.ariskatsarakis.lifedashboard.budget.def;

public interface BudgetUtilities {
    /**
     * Updates Budget each time an Income/Expense is added deleted or edited.
     */
    void updateBudget();

    /**
     * Sums all incomes and subtracts the expenses.
     * @return Budget
     */
    Budget createInitialBudget();
}
