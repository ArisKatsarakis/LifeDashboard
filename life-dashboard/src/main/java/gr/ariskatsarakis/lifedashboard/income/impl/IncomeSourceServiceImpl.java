package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSourceRepository;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class IncomeSourceServiceImpl
implements IncomeSourceService {

    @Autowired
    private IncomeSourceRepository incomeSourceRepository;
    @Override
    public Map<String, Object> getIncomeSources() {

        List<IncomeSource> incomeSources = incomeSourceRepository.findAll();


        //The map should contain
        // The sum of the incomes
        // The Incomes of the source
        // Name of the source.
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("test", incomeSources.get(0));

        return responseMap;
    }
}
