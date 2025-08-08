package gr.ariskatsarakis.organizer.incomes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.user.UserInfo;
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
                UserInfo userInfo = userInfoService.fetchUser();
                income.setUserInfo(userInfo);
                BigDecimal totalIncomes = userInfo.getTotalMoneyReceived() == null ? income.getMoney()
                                : userInfo.getTotalMoneyReceived().add(income.getMoney());
                BigDecimal totalPending = userInfo.getTotalMoneyPending() == null ? totalIncomes
                                : userInfo.getTotalMoneyPending().add(income.getMoney());
                userInfo.setTotalMoneyReceived(totalIncomes);
                userInfo.setTotalMoneyPending(totalPending);
                income = incomeRespository.save(income);
                userInfoService.updateUser(userInfo);
                incomeDTO.setIncomeId(income.getIncomeId());
                return incomeDTO;
        }

        public List<IncomeDTO> fetchIncomesByCategory(IncomeCategory category) {
                List<IncomeDTO> idtos = new ArrayList<>();
                UserInfo userInfo = userInfoService.fetchUser();
                if (userInfo == null) {
                        throw new UsernameNotFoundException("username not found");
                }

                List<Income> incomes = incomeRespository.findByUserInfoAndIncomeCategory(userInfo, category);
                for (Income i : incomes) {
                        idtos.add(fromIncomeToDTO(i));
                }

                return idtos;
        }

}
