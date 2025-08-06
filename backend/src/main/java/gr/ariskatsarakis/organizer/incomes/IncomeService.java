package gr.ariskatsarakis.organizer.incomes;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/**
 * IncomeService
 */
@Service
@Transactional
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
                if (income.getIncomeCategory() != null) {

                        dto.setCategory(income.getIncomeCategory().toString());
                }
                return dto;
        }

        public Income fromDTOToIncome(IncomeDTO dto) {
                Income income = new Income();
                income.setIncomeId(dto.getIncomeId());
                income.setMoney(dto.getMoney());
                income.setDateCreated(dto.getDateCreated());
                if (dto.getCategory() != null) {
                        income.setIncomeCategory(IncomeCategory.valueOf(dto.getCategory()));
                }
                return income;
        }

}
