package gr.ariskatsarakis.lifedashboard.income.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeDTO;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSourceRepository;

/**
 * This is the mapper for {@link Income} and {@link IncomeDTO}
 */
public class IncomeMapper {

  @Autowired
  private IncomeSourceRepository incomeSourceRepository;

  public static IncomeDTO incomeToIncomeDTO(Income income) {
    IncomeDTO dto = new IncomeDTO();
    dto.setIncomeId(income.getIncomeId());
    dto.setIncomeSourceId(income.getIncomeSource().getIncomeSourceId());
    dto.setDescription(income.getDescription());
    dto.setDateCreated(income.getDateCreated().toLocalDateTime().toLocalDate());
    dto.setMoneyReceived(income.getMoneyReceived());
    dto.setIncomeSourceName(income.getIncomeSource().getName());
    return dto;
  }

  public Income incomeDTOToIncome(IncomeDTO dto) {
    Optional<IncomeSource> incomeSource = incomeSourceRepository.findById(dto.getIncomeSourceId());

    Income income = new Income();
    if (incomeSource.isPresent()) {
      income.setIncomeSource(incomeSource.get());
    }
    income.setIncomeId(dto.getIncomeId());
    income.setDescription(dto.getDescription());
    income.setMoneyReceived(dto.getMoneyReceived());
    return income;
  }
}
