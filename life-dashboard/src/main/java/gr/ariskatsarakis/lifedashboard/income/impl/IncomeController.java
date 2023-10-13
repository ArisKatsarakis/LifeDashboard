package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeService;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private IncomeSourceService incomeSourceService;

    @GetMapping("/api/v1/incomes")
    List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @PostMapping("/api/v1/incomes")
    Income addIncome(@RequestBody Income income) {
        return incomeService.addIncome(income);
    }

    @GetMapping("api/v1/income-sources")
    ResponseEntity<Object> getAllIncomeSources() {
        Map<String, Object> responseMap = incomeSourceService.getIncomeSources();
        return   new ResponseEntity<Object>(responseMap,HttpStatus.OK);
    }

    @GetMapping("api/v1/income-sources/type/{incomeType}")
    ResponseEntity<Object> getIncomeSourceType(@PathVariable String incomeType) {
        Map<String, Object> responseMap = incomeSourceService.getIncomeSourcesByType(incomeType);
        return   new ResponseEntity<Object>(responseMap,HttpStatus.OK);
    }
}