package gr.ariskatsarakis.lifedashboard.income.def;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository  extends JpaRepository<Income, Long > {
    @Modifying
    @Query("delete from Income i where i.incomeId = ?1")
    void deleteIncomeById(long incomeId);
}
