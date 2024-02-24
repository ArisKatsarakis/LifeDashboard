package gr.ariskatsarakis.lifedashboard.expense.beans;

import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseSpecifications {
    public static Specification<Expense> byExpenseType(ExpenseType expenseType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("expenseType"), expenseType);
    }

    public static Specification<Expense> bySpecificDate(LocalDate currentDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("dateCreated"), currentDate);
    }

    public static Specification<Expense> byMmaxMoneySpent(BigDecimal maxMoney) {
	    return (root, query, criteriaBuilder) -> 
		    criteriaBuilder.lessThanOrEqualTo(root.get("moneySpent"), maxMoney);
    }
}
