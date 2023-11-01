package gr.ariskatsarakis.lifedashboard.income.def;

import java.util.List;

/**
 * This Service contains the Business Logic for {@link Income}
 */

public interface IncomeService {

    /**
     * Returns all Incomes.
     */
    List<Income> getAllIncomes();

    /**
     * Adds the Income in param to the DB.
     *
     * @param income
     * @param incomeSourceId
     * @return The newly created Income.
     */
    Income addIncome(Income income, Long incomeSourceId);

    /**
     * Gets the Incomes that have the same Stability
     * @param stability
     * @return Same stability income.
     */
    List<Income> getByIncomeByStability(Stability stability);


    /**
     * Get Incomes by Source.
     * @param incomeSource the IncomeSource.
     * @return List of Incomes by IncomeSource.
     */
    List<Income> getByIncomeBySource(IncomeSource incomeSource);


}
