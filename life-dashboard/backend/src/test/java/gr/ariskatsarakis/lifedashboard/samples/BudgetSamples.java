package gr.ariskatsarakis.lifedashboard.samples;

import gr.ariskatsarakis.lifedashboard.budget.def.Budget;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Samples for Budget
 */
public class BudgetSamples {

    public static Budget sampleBudget() {
        Budget budget = new Budget();
        budget.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        budget.setWalletMoney(BigDecimal.valueOf(200l));
        budget.setLastIncomeDate(Timestamp.valueOf(LocalDateTime.now().minusYears(1)));
        budget.setLastExpenseDate(Timestamp.valueOf(LocalDateTime.now()));
        return budget;
    }

}
