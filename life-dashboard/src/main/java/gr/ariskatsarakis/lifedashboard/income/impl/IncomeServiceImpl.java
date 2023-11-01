package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class IncomeServiceImpl
implements IncomeService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeSourceRepository incomeSourceRepository;
    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public Income addIncome(Income income, Long incomeSourceId) {
        Optional<IncomeSource> iSource = incomeSourceRepository.findById(incomeSourceId);
        income.setIncomeSource(iSource.orElse(null));
        logger.info("New Income Added " + income.toString() + " TIME: " + Timestamp.valueOf(LocalDateTime.now()));
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
