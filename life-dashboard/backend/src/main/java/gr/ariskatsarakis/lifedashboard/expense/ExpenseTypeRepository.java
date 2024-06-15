package gr.ariskatsarakis.lifedashboard.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ExpenseTypeRepository
 */
@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {

}
