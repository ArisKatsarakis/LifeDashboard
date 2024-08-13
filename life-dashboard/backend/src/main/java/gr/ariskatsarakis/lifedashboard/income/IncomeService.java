package gr.ariskatsarakis.lifedashboard.income;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IncomeService
 */
@Service
public class IncomeService {

  private Logger incomeLogger = LoggerFactory.getLogger(IncomeService.class);

  @Autowired
  private IncomeRepository incomeRepository;

  public List<Income> getIncomes() {
    return incomeRepository.findAll();
  }

  public Income addIncome(Income income) {
    income.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    return incomeRepository.save(income);
  }

}
