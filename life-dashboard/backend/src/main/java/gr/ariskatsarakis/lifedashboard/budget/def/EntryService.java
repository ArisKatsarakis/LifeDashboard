package gr.ariskatsarakis.lifedashboard.budget.def;

import java.util.List;

/**
 * Service of Entries
 */
public interface EntryService {
    /**
     *
     * @return last 10 entries according their date Created
     */
    List<Entry> getLast10Entries();

    /**
     * Add new Entry
     * @param entry
     * @return Entry with responsive ID
     */
    Entry addNewEntry(Entry entry);


    /**
     * Updates Entry
     * @param entry
     * @return updated Entry
     */
    Entry updateEntry(Entry entry);

    /**
     *
     * Delete Entry by using id
     * @param entryId
     */
    void deleteEntryById(long entryId);

    /**
     * Delete Entry according to Income ID
     */
    void deleteEntryByIncomeId(long incomeId);


    /**
     * Delete Entry according to Expense ID
     * @param expenseId
     */
    void deleteEntryByExpenseId(long expenseId);
}
