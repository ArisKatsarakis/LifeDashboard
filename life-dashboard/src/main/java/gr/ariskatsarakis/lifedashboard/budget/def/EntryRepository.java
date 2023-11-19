package gr.ariskatsarakis.lifedashboard.budget.def;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("Select entry from Entry entry  order by entry.dateInserted")
    List<Entry> getLast10(Pageable last10);

    @Query("select entry from Entry entry where entry.incomeId = ?1")
    Entry getEntryByIncomeId(long incomeId);

    @Query("select entry from Entry entry where entry.expenseId = ?1")
    Entry getEntryByExpenseId(long expenseId);
}
