package gr.ariskatsarakis.lifedashboard.budget.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.ariskatsarakis.lifedashboard.budget.beans.BudgetHistory;
import gr.ariskatsarakis.lifedashboard.budget.def.Budget;
import gr.ariskatsarakis.lifedashboard.budget.def.BudgetUtilities;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller for {@link Budget}
 *
 */
@RestController
public class BudgetController {

  @Autowired
  private BudgetUtilities budgetService;

  @GetMapping("api/v1/budget/history")
  @CrossOrigin
  public BudgetHistory budgetHistory() {
    return budgetService.getBudgetHistory();
  }

  @GetMapping("/csrf-token")
  public CsrfToken getPublicToken(HttpServletRequest request) {
    return (CsrfToken) request.getAttribute("_csrf");
  }

}
