package gr.ariskatsarakis.lifedashboard.income;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.wallet.WalletService;

/**
 * IncomeService
 */
@Service
public class IncomeService {

  private Logger incomeLogger = LoggerFactory.getLogger(IncomeService.class);

  @Autowired
  private IncomeRepository incomeRepository;

  @Autowired
  private WalletService walletService;

  public List<Income> getIncomes() {
    return incomeRepository.findAll();
  }

  public Income addIncome(Income income) {
    income.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    walletService.addNewIncomeAtWallet(income);
    return incomeRepository.save(income);
  }

  public Income getIncomeById(Long incomeId) {
    Optional<Income> optional = incomeRepository.findById(incomeId);

    if (optional.isPresent()) {
      return optional.get();
    }
    return null;
  }

}
