package gr.ariskatsarakis.lifedashboard.income.impl;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import gr.ariskatsarakis.lifedashboard.income.def.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/api/v1/incomes")
    List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }
}