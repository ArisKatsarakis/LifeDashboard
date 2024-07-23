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
    if (checkIfIncomesExistForThisMonth(income) > 0) {
      throw new RuntimeException("Too many Incomes this month");
    }

    return incomeRepository.save(income);
  }

  private Integer checkIfIncomesExistForThisMonth(Income income) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2024, 06, 01);
    Timestamp start = new Timestamp(calendar.getTime().getTime());
    calendar.set(2024, 06, 31);
    Timestamp end = new Timestamp(calendar.getTime().getTime());
    incomeLogger.info("START: " + start.toString());
    incomeLogger.info("END: " + end.toString());
    incomeLogger.info(incomeRepository.getIncomesForMonth(start, end).toString());
    return incomeRepository.getIncomesForMonth(start, end);
  }

}
