package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class IncomeSourceServiceImpl
implements IncomeSourceService {

    @Autowired
    private IncomeSourceRepository incomeSourceRepository;
    @Override
    public Map<String, Object> getIncomeSources() {
        List<IncomeSource> incomeSources = incomeSourceRepository.findAll();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("sources", incomeSources);
        return responseMap;
    }

    @Override
    public Map<String, Object> getIncomeSourcesByType(String incomeType) {
        /**
         * Todo Create it with a Specifications.
         */

        List<IncomeSource> incomeSources = incomeSourceRepository.findAll();

        IncomeSource specificSource = incomeSources.stream().filter(incomeSource ->
            incomeSource.getIncomeType() == IncomeType.CODING ).findFirst().orElse(null);
        Map<String, Object> responseMap = new HashMap<>();
        if (specificSource != null) {
            BigDecimal sum = calculateIncomesSum(specificSource.getIncomeSet());

            responseMap.put("incomeSum", sum);
        }
        responseMap.put("incomeSource", specificSource);

        return responseMap;
    }

    BigDecimal calculateIncomesSum(Set<Income> incomeSet) {
        BigDecimal sum = BigDecimal.ZERO;

        incomeSet.stream().forEach(
                income ->  sum.add(income.getMoneyReceived())
        );

        return sum;
    }
}
