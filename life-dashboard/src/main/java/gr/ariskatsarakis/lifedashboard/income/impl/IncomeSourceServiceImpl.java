package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSourceRepository;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IncomeSourceServiceImpl
implements IncomeSourceService {

    @Autowired
    private IncomeSourceRepository incomeSourceRepository;
    @Override
    public List<IncomeSource> getIncomeSources() {

        return incomeSourceRepository.findAll();
    }
}
