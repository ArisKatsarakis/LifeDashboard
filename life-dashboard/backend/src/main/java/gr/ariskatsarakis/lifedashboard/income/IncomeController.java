package gr.ariskatsarakis.lifedashboard.income;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IncomeController
 */
@RestController
@RequestMapping("/api/v1")
public class IncomeController {

  @Autowired
  private IncomeService incomeService;

  @GetMapping("/incomes")
  public List<Income> getIncomes() {
    return incomeService.getIncomes();
  }

  @PostMapping("/incomes")
  public Income addIncome(@RequestBody Income income) {
    return incomeService.addIncome(income);
  }
}
