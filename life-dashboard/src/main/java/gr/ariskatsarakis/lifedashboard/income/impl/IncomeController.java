package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeService;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSource;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    List<IncomeSource> getAllIncomeSources() {
        return incomeSourceService.getIncomeSources();
    }
}