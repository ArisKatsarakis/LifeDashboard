package gr.ariskatsarakis.lifedashboard.income.beans;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;

/**
 * Specifications for Incomes
 * Using Criteria for expensive queries
 */
public class IncomeSpecifications {

    /**
     * Get Specification for after specific Timestamp
     * @param start
     * @return
     */

    public static Specification<Income> afterDate(Timestamp start) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("dateCreated"), start);

    }

    /**
     * Get Incomes that are before date setted.
     * @param end
     * @return
     */

    public static Specification<Income>  beforeDate(Timestamp end ) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("dateCreated"), end);
    }
}
