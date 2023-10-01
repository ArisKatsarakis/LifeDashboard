package gr.ariskatsarakis.lifedashboard.expense.def;

import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    //empty
}