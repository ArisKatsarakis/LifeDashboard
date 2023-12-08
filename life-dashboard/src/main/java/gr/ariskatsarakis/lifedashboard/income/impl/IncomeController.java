package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseRepository;
import gr.ariskatsarakis.lifedashboard.income.def.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private IncomeSourceService incomeSourceService;
    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/api/v1/incomes")
    @CrossOrigin
    List<IncomeDTO> getAllIncomes() {
        return incomeService.getAllIncomesToDtos();
    }

    @PostMapping("/api/v1/{incomeSource}/incomes")
    @CrossOrigin
    Income addIncome(@PathVariable(value = "incomeSource")Long incomeSourceId, @RequestBody Income income) {
        return incomeService.addIncome(income , incomeSourceId);
    }

    @GetMapping("api/v1/income-sources")
    @CrossOrigin
    ResponseEntity<Object> getAllIncomeSources() {
        Map<String, Object> responseMap = incomeSourceService.getIncomeSources();
        return   new ResponseEntity<Object>(responseMap,HttpStatus.OK);
    }

    @PostMapping("api/v1/income-sources")
    IncomeSource  addIncomeSource(@RequestBody IncomeSource incomeSource) {
        return incomeSourceService.addIncomeSource(incomeSource);
    }
    @GetMapping("api/v1/income-sources/type/{incomeType}")
    ResponseEntity<Object> getIncomeSourceType(@PathVariable String incomeType) {
        Map<String, Object> responseMap = incomeSourceService.getIncomeSourcesByType(incomeType);
        return   new ResponseEntity<Object>(responseMap,HttpStatus.OK);
    }

    @GetMapping("api/v1/incomeDto/{id}")
    @CrossOrigin
    ResponseEntity<IncomeDTO> getIncomeDTO(@PathVariable Long id) {
        Optional<Income> income = incomeService.fetchIncomeById(id);
        if(income.isPresent()) {
            IncomeDTO incomeDTO = IncomeMapper.incomeToIncomeDTO(income.get());
            return new ResponseEntity<>(incomeDTO, HttpStatus.OK);
        }
        return null;
    }

    @PutMapping("api/v1/incomeDto/{incomeId}")
    @CrossOrigin
    ResponseEntity<IncomeDTO> updateIncomeThroughDTO(@PathVariable Long incomeId, @RequestBody IncomeDTO dto) {
        Optional<IncomeSource> incomeSource = incomeSourceService.findByID(dto.getIncomeSourceId());
        if(incomeSource.isPresent()) {
            Optional<Income> income = incomeService.fetchIncomeById(incomeId);
            if(income.isPresent()) {
                income.get().setIncomeSource(incomeSource.get());
                income.get().setDescription(dto.getDescription());
                income.get().setDateCreated(Timestamp.valueOf(dto.getDateCreated().atTime(LocalTime.now())));
                income.get().setMoneyReceived(dto.getMoneyReceived());
                return incomeService.updateIncome(income.get());
            }
        }
        return new ResponseEntity<IncomeDTO>(new IncomeDTO(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("api/v1/incomes/{incomeId}")
    @CrossOrigin
    void deleteIncomeById(@PathVariable long incomeId) {
         incomeService.deleteIncomeById(incomeId);
    }

    @GetMapping("api/v1/incomes-last-10")
    @CrossOrigin
    List<IncomeDTO> getLast10Incomes() {
        return incomeService.getLast10Incomes();
    }

    //TODO Get Incomes Using Criteria.


}