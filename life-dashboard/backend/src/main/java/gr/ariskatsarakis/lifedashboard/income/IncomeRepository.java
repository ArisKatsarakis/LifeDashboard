package gr.ariskatsarakis.lifedashboard.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * IncomeRepository
 */
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

}
