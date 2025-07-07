package gr.ariskatsarakis.organizer.incomes;

import org.springframework.stereotype.Service;

/**
 * IncomeService
 */
@Service
public class IncomeService {

	private IncomeRespository incomeRespository;

	public IncomeService(IncomeRespository incomeRespository) {
		this.incomeRespository = incomeRespository;
	}

	public Income addIncome(Income income) {
		return incomeRespository.save(income);
	}

	public IncomeDTO fromIncomeToDTO(Income income) {
		IncomeDTO dto = new IncomeDTO();
		dto.setIncomeId(income.getIncomeId());
		dto.setMoney(income.getMoney());
		dto.setDateCreated(income.getDateCreated());
		return dto;
	}

	public Income fromDTOToIncome(IncomeDTO dto) {
		Income income = new Income();
		income.setIncomeId(dto.getIncomeId());
		income.setMoney(dto.getMoney());
		income.setDateCreated(dto.getDateCreated());
		return income;
	}

}
