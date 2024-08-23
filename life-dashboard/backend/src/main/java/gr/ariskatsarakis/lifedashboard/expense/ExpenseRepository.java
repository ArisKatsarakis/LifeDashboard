package gr.ariskatsarakis.lifedashboard.expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * ExpenseRepository
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
