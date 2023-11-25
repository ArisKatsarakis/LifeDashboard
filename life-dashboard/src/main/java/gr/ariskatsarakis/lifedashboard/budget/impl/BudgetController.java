package gr.ariskatsarakis.lifedashboard.budget.impl;

import gr.ariskatsarakis.lifedashboard.budget.beans.BudgetHistory;
import gr.ariskatsarakis.lifedashboard.budget.def.Budget;
import gr.ariskatsarakis.lifedashboard.budget.def.BudgetUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for {@link Budget}
 *
 */
@RestController
public class BudgetController {

    @Autowired
    private BudgetUtilities budgetService;


    @GetMapping("api/v1/budget/history")
    public BudgetHistory budgetHistory() {
        return  budgetService.getBudgetHistory();
    }
}
