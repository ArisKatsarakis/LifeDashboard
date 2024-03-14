package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.beans.IncomeStatsDTO;
import gr.ariskatsarakis.lifedashboard.income.def.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("api/v1/incomes")
@RestController
public class IncomeController {

  @Autowired
  private IncomeService incomeService;

  @Autowired
  private IncomeSourceService incomeSourceService;

  private Logger logger = LoggerFactory.getLogger(getClass());

  @GetMapping
  @CrossOrigin
  List<IncomeDTO> getAllIncomes() {
    return incomeService.getAllIncomesToDtos();
  }

  @PostMapping("/{incomeSource}")
  @CrossOrigin
  Income addIncome(@PathVariable(value = "incomeSource") Long incomeSourceId, @RequestBody Income income) {
    return incomeService.addIncome(income, incomeSourceId);
  }

  @GetMapping("/income-sources")
  @CrossOrigin
  ResponseEntity<Object> etAllIncomeSources() {
    Map<String, List<IncomeSource>> responseMap = incomeSourceService.getIncomeSources();
    return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
  }

  @PostMapping("/income-sources")
  @CrossOrigin
  IncomeSource addIncomeSource(@RequestBody IncomeSource incomeSource) {
    return incomeSourceService.addIncomeSource(incomeSource);
  }

  @GetMapping("api/v1/income-sources/type/{incomeType}")
  ResponseEntity<Object> getIncomeSourceType(@PathVariable String incomeType) {
    Map<String, Object> responseMap = incomeSourceService.getIncomeSourcesByType(incomeType);
    return new ResponseEntity<Object>(responseMap, HttpStatus.OK);
  }

  @GetMapping("/incomeDto/{id}")
  @CrossOrigin
  ResponseEntity<IncomeDTO> getIncomeDTO(@PathVariable Long id) {
    Optional<Income> income = incomeService.fetchIncomeById(id);
    if (income.isPresent()) {
      IncomeDTO incomeDTO = IncomeMapper.incomeToIncomeDTO(income.get());
      return new ResponseEntity<>(incomeDTO, HttpStatus.OK);
    }
    return null;
  }

  @PutMapping("/incomeDto/{incomeId}")
  @CrossOrigin
  ResponseEntity<IncomeDTO> updateIncomeThroughDTO(@PathVariable Long incomeId, @RequestBody IncomeDTO dto) {
    Optional<IncomeSource> incomeSource = incomeSourceService.findByID(dto.getIncomeSourceId());
    if (incomeSource.isPresent()) {
      Optional<Income> income = incomeService.fetchIncomeById(incomeId);
      if (income.isPresent()) {
        income.get().setIncomeSource(incomeSource.get());
        income.get().setDescription(dto.getDescription());
        income.get().setDateCreated(Timestamp.valueOf(dto.getDateCreated().atTime(LocalTime.now())));
        income.get().setMoneyReceived(dto.getMoneyReceived());
        return incomeService.updateIncome(income.get());
      }
    }
    return new ResponseEntity<IncomeDTO>(new IncomeDTO(), HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{incomeId}")
  @CrossOrigin
  void deleteIncomeById(@PathVariable long incomeId) {
    incomeService.deleteIncomeById(incomeId);
  }

  @GetMapping("-last-10")
  @CrossOrigin
  List<IncomeDTO> getLast10Incomes() {
    return incomeService.getLast10Incomes();
  }

  @GetMapping("/month/{monthSelected}")
  @CrossOrigin
  List<IncomeDTO> getIncomesOfMothSelected(@PathVariable Integer monthSelected) {
    logger.info("MONTH SELECTED " + monthSelected);

    return incomeService.getIncomesByMonth(monthSelected);
  }

  @GetMapping("/stats")
  @CrossOrigin
  public List<IncomeStatsDTO> getAllIncomeStats() {
    List<IncomeStatsDTO> incomeStatsDTOs = new ArrayList<>();
    List<IncomeSource> incomeSources = incomeSourceService.getIncomeSources().get("sources");
    incomeSources.stream().forEach(
        source -> {
          IncomeStatsDTO incomeStatsDTO = new IncomeStatsDTO();
          incomeStatsDTO.setIncomes(new ArrayList<>(source.getIncomeSet()));
          BigDecimal sum = incomeStatsDTO.getIncomes().stream()
              .map(income -> income.getMoneyReceived())
              .reduce(BigDecimal.ZERO, (first, second) -> first.add(second));
          incomeStatsDTO.setMoneySum(sum);
          incomeStatsDTO.setIncomeType(source.getIncomeType());
          incomeStatsDTOs.add(incomeStatsDTO);
        });
    return incomeStatsDTOs;

  }
  // TODO Get Incomes Using Criteria.
}
