package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.budget.def.BudgetUtilities;
import gr.ariskatsarakis.lifedashboard.budget.def.Entry;
import gr.ariskatsarakis.lifedashboard.budget.def.EntryService;
import gr.ariskatsarakis.lifedashboard.budget.impl.EntryMapper;
import gr.ariskatsarakis.lifedashboard.income.beans.IncomeSpecifications;
import gr.ariskatsarakis.lifedashboard.income.def.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class IncomeServiceImpl
implements IncomeService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeSourceRepository incomeSourceRepository;

    @Autowired
    private BudgetUtilities budgetUtilities;

    @Autowired
    private EntryService entryService;
    public List<IncomeDTO> getAllIncomesToDtos() {
        List<IncomeDTO> incomeDTOS = new ArrayList<IncomeDTO>();
        incomeRepository.findAll().stream().forEach(income -> incomeDTOS.add(IncomeMapper.incomeToIncomeDTO(income)));
        return  incomeDTOS;
    }

    @Override
    public Income addIncome(Income income, Long incomeSourceId) {
        Optional<IncomeSource> iSource = incomeSourceRepository.findById(incomeSourceId);
        income.setIncomeSource(iSource.orElse(null));
        logger.info("New Income Added " + income + " TIME: " + Timestamp.valueOf(LocalDateTime.now()));
        income.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        Income savedIncome = incomeRepository.save(income);
        Entry entry = EntryMapper.incomeToEntry(savedIncome);
        entryService.addNewEntry(entry);
        return savedIncome;
    }

    @Override
    public List<Income> getByIncomeByStability(Stability stability) {
        return null;
    }

    @Override
    public List<Income> getByIncomeBySource(IncomeSource incomeSource) {
        return null;
    }

    @Override
    public Optional<Income> fetchIncomeById(Long incomeId) {
        return incomeRepository.findById(incomeId);
    }

    @Override
    public ResponseEntity<IncomeDTO> updateIncome(Income income) {
        Income update = incomeRepository.save(income);
        IncomeDTO incomeDTO = IncomeMapper.incomeToIncomeDTO(update);
        Entry entry = EntryMapper.incomeToEntry(update);
        entryService.updateEntry(entry);
        return new ResponseEntity<IncomeDTO>(incomeDTO, HttpStatus.OK);
    }

    @Override
    public void deleteIncomeById(long incomeId) {
        entryService.deleteEntryByIncomeId(incomeId);
        incomeRepository.deleteIncomeById(incomeId);
    }

    @Override
    public List<IncomeDTO> getLast10Incomes() {
        List<IncomeDTO> tenIncomeDtos = new ArrayList<>();
        Pageable last10 = PageRequest.of(0, 10);
        List<Income>  tenIncomes = incomeRepository.getLast10(last10);
        tenIncomes.stream().forEach(
                income -> {
                    IncomeDTO incomeDTO = IncomeMapper.incomeToIncomeDTO(income);
                    tenIncomeDtos.add(incomeDTO);
                }
        );
        return tenIncomeDtos;
    }

    @Override
    public List<IncomeDTO> getIncomesByMonth(Integer month) {
        LocalDate monthSelected = LocalDate.of(LocalDate.now().getYear(), month, 1);
        Integer endMonth = monthSelected.lengthOfMonth();
        LocalDateTime end = LocalDateTime.of(2023, month, endMonth, 0,0,0 );

        Specification spec = IncomeSpecifications.afterDate(Timestamp.valueOf(monthSelected.atTime(LocalTime.of(0,0,0))));
        spec  = spec.and(IncomeSpecifications.beforeDate(Timestamp.valueOf(end)));

        List<Income> incomesByMonth = incomeRepository.findAll(spec);
        List<IncomeDTO> incomeDtos = new ArrayList<>();

        incomesByMonth.stream().forEach(
                income -> incomeDtos.add(IncomeMapper.incomeToIncomeDTO(income))
        );
        return incomeDtos;
    }
}
