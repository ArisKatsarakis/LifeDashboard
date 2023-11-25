package gr.ariskatsarakis.lifedashboard.budget.def;

public interface BudgetUtilities {
    /**
     * Updates Budget each time an Income/Expense is added deleted or edited.
     */
    void updateBudget( Entry entry);

    /**
     * Sums all incomes and subtracts the expenses.
     * @return Budget
     */
    Budget createInitialBudget();


    /**
     * Creates the new last budget using the last entry created or updated.
     *
     * @param lastBudget
     * @param entry      The new entry expenses or income
     * @return the last Budget
     */

    Budget createBudgetFromLastEntry(Budget lastBudget, Entry entry);

    /**
     * When an entry is deleted we re-run all the entries.
     * make the new budget accordingly
     */
    void refreshBudget();
}
