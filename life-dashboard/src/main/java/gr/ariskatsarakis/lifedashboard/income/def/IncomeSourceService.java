package gr.ariskatsarakis.lifedashboard.income.def;

import java.util.List;
import java.util.Map;

public interface IncomeSourceService {

    /**
     * Fetch Income Sources;
     * @return
     */
    Map<String, Object> getIncomeSources();
}
