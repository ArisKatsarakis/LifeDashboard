package gr.ariskatsarakis.lifedashboard.income.def;

import java.util.List;

public interface IncomeSourceService {

    /**
     * Fetch Income Sources;
     * @return
     */
    List<IncomeSource> getIncomeSources();
}
