package gr.ariskatsarakis.lifedashboard.budget.def;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> , JpaSpecificationExecutor<Budget> {
    @Query("select  bud from Budget bud order by bud.dateCreated desc")
    List<Budget> getLastBudget(Pageable latest);
}
