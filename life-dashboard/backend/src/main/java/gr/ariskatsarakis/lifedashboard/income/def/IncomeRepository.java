package gr.ariskatsarakis.lifedashboard.income.def;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>, JpaSpecificationExecutor<Income> {
  @Modifying
  @Query("delete from Income i where i.incomeId = ?1")
  void deleteIncomeById(long incomeId);

  @Query("select inc from Income inc ORDER BY inc.dateCreated")
  List<Income> getLast10(Pageable last10);

  @Query("select inc from Income inc " +
      "where inc.dateCreated > ?1 ")
  List<Income> getIncomesOfTheMonth(Timestamp month);
}
