package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IncomeServiceImpl
implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> getByIncomeByStability(Stability stability) {
        return null;
    }

    @Override
    public List<Income> getByIncomeBySource(IncomeSource incomeSource) {
        return null;
    }
}