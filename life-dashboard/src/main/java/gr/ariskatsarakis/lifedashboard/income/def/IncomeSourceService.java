package gr.ariskatsarakis.lifedashboard.income.def;

import java.util.Map;
import java.util.Optional;

public interface IncomeSourceService {

    /**
     * Fetch Income Sources;
     * @return
     */
    Map<String, Object> getIncomeSources();

    Map<String, Object> getIncomeSourcesByType(String incomeType);

    /**
     * Fetch Income Source by ID
     * @param incomeSourceId
     * @return
     */
    Optional<IncomeSource> findByID(Long incomeSourceId);
}
