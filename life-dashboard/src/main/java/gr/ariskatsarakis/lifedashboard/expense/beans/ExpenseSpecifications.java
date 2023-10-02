package gr.ariskatsarakis.lifedashboard.expense.beans;

import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import org.hibernate.query.criteria.spi.CriteriaBuilderExtension;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ExpenseSpecifications {
    public static Specification<Expense> byExpenseType(ExpenseType expenseType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("expenseType"), expenseType);
    }

    public static Specification<Expense> byMonth(LocalDate mothDate) {
        int specificMonth = mothDate.getMonthValue();
         return (root, query, criteriaBuilder) ->
        {
             return criteriaBuilder.equal(
                    criteriaBuilder.function("EXTRACT", Integer.class,
                            criteriaBuilder.literal("MONTH"), root.get("dateCreated")),
                    specificMonth
            );
        };
    }
}
