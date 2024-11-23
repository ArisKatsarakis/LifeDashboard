package gr.ariskatsarakis.lifedashboard.income;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<List<Income>> getIncomes(Pageable pageable) {
    ResponseEntity<List<Income>> incomes = new ResponseEntity<List<Income>>(incomeService.getIncomes(pageable),
        HttpStatus.OK);
    return incomes;
  }

  @PostMapping("/incomes")
  public ResponseEntity<Income> addIncome(@RequestBody Income income) {
    ResponseEntity<Income> incomeCreate = new ResponseEntity<Income>(incomeService.addIncome(income),
        HttpStatus.CREATED);
    return incomeCreate;
  }

  @GetMapping("/incomes/{incomeId}")
  public ResponseEntity<Income> getIncomes(@PathVariable Long incomeId) {
    Income income = incomeService.getIncomeById(incomeId);
    ResponseEntity<Income> incomeResponse = new ResponseEntity<>(income,
        income == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);

    return incomeResponse;
  }
}
