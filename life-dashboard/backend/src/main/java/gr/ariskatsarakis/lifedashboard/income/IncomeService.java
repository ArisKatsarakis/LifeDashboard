package gr.ariskatsarakis.lifedashboard.income;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.lifedashboard.wallet.WalletService;

/**
 * IncomeService
 */
@Service
public class IncomeService {

  @Autowired
  private IncomeRepository incomeRepository;

  @Autowired
  private WalletService walletService;

  public List<Income> getIncomes(Pageable pageable) {
    return incomeRepository.findAll(PageRequest.of(
        pageable.getPageNumber(),
        pageable.getPageSize(),
        Sort.by("incomeType").descending())).getContent();
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

  public List<Income> getMonthlyIncomes(Integer month) {
    List<Income> monthlyIncomes = incomeRepository.findByMonth(month);
    return monthlyIncomes;
  }

}
