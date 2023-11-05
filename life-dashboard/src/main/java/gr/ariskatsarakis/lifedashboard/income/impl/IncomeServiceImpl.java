package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class IncomeServiceImpl
implements IncomeService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeSourceRepository incomeSourceRepository;
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public Income addIncome(Income income, Long incomeSourceId) {
        Optional<IncomeSource> iSource = incomeSourceRepository.findById(incomeSourceId);
        income.setIncomeSource(iSource.orElse(null));
        logger.info("New Income Added " + income + " TIME: " + Timestamp.valueOf(LocalDateTime.now()));
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

    @Override
    public Optional<Income> fetchIncomeById(Long incomeId) {
        return incomeRepository.findById(incomeId);
    }

    @Override
    public ResponseEntity<IncomeDTO> updateIncome(Income income) {
        Income update = incomeRepository.save(income);
        IncomeDTO incomeDTO = IncomeMapper.incomeToIncomeDTO(update);
        return new ResponseEntity<IncomeDTO>(incomeDTO, HttpStatus.OK);
    }
}
