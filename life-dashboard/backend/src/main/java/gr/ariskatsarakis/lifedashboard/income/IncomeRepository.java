package gr.ariskatsarakis.lifedashboard.income;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * IncomeRepository
 */
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

  @Query("Select count(e.incomeId) from Income e where e.timestamp >= ?1 and e.timestamp <= ?2 ")
  public Integer getIncomesForMonth(Timestamp start, Timestamp end);

}
