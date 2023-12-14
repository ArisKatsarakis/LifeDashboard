package gr.ariskatsarakis.lifedashboard.income.def;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IncomeSourceService {

    /**
     * Fetch Income Sources;
     * @return
     */
    Map<String, List<IncomeSource>> getIncomeSources();

    Map<String, Object> getIncomeSourcesByType(String incomeType);

    /**
     * Fetch Income Source by ID
     * @param incomeSourceId
     * @return
     */
    Optional<IncomeSource> findByID(Long incomeSourceId);


    /**
     * Creates new Income Source
     * @param incomeSource
     * @return the Income Source created
     */
    IncomeSource addIncomeSource(IncomeSource incomeSource);

}
