package gr.ariskatsarakis.organizer.incomes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * IncomeRespository
 */
@Repository
public interface IncomeRespository extends JpaRepository<Income, Long> {

}
