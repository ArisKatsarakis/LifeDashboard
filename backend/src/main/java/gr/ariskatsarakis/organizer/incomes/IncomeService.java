package gr.ariskatsarakis.organizer.incomes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

/**
 * IncomeService
 */
@Service
@Transactional
public class IncomeService {

        private IncomeRespository incomeRespository;
        private UserInfoService userInfoService;

        public IncomeService(IncomeRespository incomeRespository, UserInfoService userInfoService) {
                this.incomeRespository = incomeRespository;
                this.userInfoService = userInfoService;
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

        public List<IncomeDTO> fetchUserIncomes() {
                List<Income> incomes = incomeRespository.findByUserInfo(this.userInfoService.fetchUser());
                List<IncomeDTO> incomeDTOs = new ArrayList<>();
                for (Income i : incomes) {
                        incomeDTOs.add(fromIncomeToDTO(i));
                }
                return incomeDTOs;

        }

        public IncomeDTO addIncome(IncomeDTO incomeDTO) {
                Income income = fromDTOToIncome(incomeDTO);
                income.setUserInfo(userInfoService.fetchUser());
                income = incomeRespository.save(income);
                incomeDTO.setIncomeId(income.getIncomeId());
                return incomeDTO;
        }

}
