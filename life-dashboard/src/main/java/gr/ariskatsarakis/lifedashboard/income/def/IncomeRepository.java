package gr.ariskatsarakis.lifedashboard.income.def;

import gr.ariskatsarakis.lifedashboard.income.def.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository  extends JpaRepository<Income, Long > {
}
