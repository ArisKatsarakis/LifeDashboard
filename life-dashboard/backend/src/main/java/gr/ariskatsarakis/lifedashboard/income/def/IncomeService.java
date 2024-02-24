package gr.ariskatsarakis.lifedashboard.income.def;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * This Service contains the Business Logic for {@link Income}
 */

public interface IncomeService {

    /**
     * Returns all Incomes.
     */
    List<IncomeDTO> getAllIncomesToDtos();

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



    Optional<Income> fetchIncomeById(Long incomeId);

    /**
     * Update Income
     * @param income
     * @return
     */

    ResponseEntity<IncomeDTO> updateIncome(Income income);

    /**
     * Delete Income by incomeId
     * @param incomeId
     */

    void deleteIncomeById(long incomeId);

    List<IncomeDTO> getLast10Incomes();

    /**
     * Fetches Incomes according to the month created
     * @param month
     * @return Incomes in DTO Formation for the selected month
     */
    List<IncomeDTO> getIncomesByMonth(Integer month);
}
