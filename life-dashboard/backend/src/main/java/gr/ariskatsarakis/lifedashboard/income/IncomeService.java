package gr.ariskatsarakis.lifedashboard.income;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IncomeService
 */
@Service
public class IncomeService {

  @Autowired
  private IncomeRepository incomeRepository;

  public List<Income> getIncomes() {
    return incomeRepository.findAll();
  }

  public Income addIncome(Income income) {
    income.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    checkIfIncomesExistForThisMonth(income);
    return incomeRepository.save(income);
  }

  private void checkIfIncomesExistForThisMonth(Income income) {

  }
}
