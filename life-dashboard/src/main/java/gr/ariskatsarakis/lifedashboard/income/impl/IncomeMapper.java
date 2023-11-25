package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * This is the mapper for {@link Income} and {@link  IncomeDTO}
 */
public class IncomeMapper {

    @Autowired
    private IncomeSourceRepository incomeSourceRepository;
    public static IncomeDTO incomeToIncomeDTO(Income income) {
         IncomeDTO dto =  new IncomeDTO(
                income.getIncomeId(),
                income.getIncomeSource().getIncomeSourceId(),
                income.getDescription(),
                income.getDateCreated().toLocalDateTime().toLocalDate(),
                income.getMoneyReceived(),
                 income.getIncomeSource().getName()
        );

        return dto;
    }


    public  Income incomeDTOToIncome(IncomeDTO dto) {
        Optional<IncomeSource> incomeSource = incomeSourceRepository.findById(dto.getIncomeSourceId());

        Income income = new Income();
        if( incomeSource.isPresent()) {
            income.setIncomeSource(incomeSource.get());
        }
        income.setIncomeId(dto.getIncomeId());
        income.setDescription(dto.getDescription());
        income.setMoneyReceived(dto.getMoneyReceived());
        return income;
    }
}
