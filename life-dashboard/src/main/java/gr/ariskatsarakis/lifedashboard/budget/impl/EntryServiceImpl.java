package gr.ariskatsarakis.lifedashboard.budget.impl;

import gr.ariskatsarakis.lifedashboard.budget.def.BudgetUtilities;
import gr.ariskatsarakis.lifedashboard.budget.def.Entry;
import gr.ariskatsarakis.lifedashboard.budget.def.EntryRepository;
import gr.ariskatsarakis.lifedashboard.budget.def.EntryService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntryServiceImpl implements EntryService {
    private static final Logger logger = LoggerFactory.getLogger(EntryServiceImpl.class);
    @Autowired
    EntryRepository entryRepository;

    @Autowired
    BudgetUtilities budgetUtilities;
    @Override
    public List<Entry> getLast10Entries() {
        Pageable last10 = PageRequest.of(0,10);
        return entryRepository.getLast10(last10);
    }

    @Override
    public Entry addNewEntry(Entry entry) {
        logger.info("NEW ENTRY ADDED " + Timestamp.valueOf(LocalDateTime.now()) + " TYPE OF " + entry.getEntryType());
        Entry entrySaved = entryRepository.save(entry);
        budgetUtilities.updateBudget();
        return entrySaved;
    }

    @Override
    public Entry updateEntry(Entry entry) {
        if(entry.getEntryId() == 0l && entry.getIncomeId() != 0l) {
            Entry fetchedEntryforId = entryRepository.getEntryByIncomeId(entry.getIncomeId());
            entry.setEntryId(fetchedEntryforId.getEntryId());
        }else if(entry.getEntryId() == 0l && entry.getExpenseId() != 0l) {
            Entry fetchedEntryForId = entryRepository.getEntryByExpenseId(entry.getExpenseId());
            entry.setEntryId(fetchedEntryForId.getEntryId());
        }
        logger.info("ENTRY WITH ID:" + entry.getEntryId() +  " UPDATED TIME: " + Timestamp.valueOf(LocalDateTime.now()));
        Entry entrySaved = entryRepository.save(entry);
        budgetUtilities.updateBudget();
        return entrySaved;
    }

    @Override
    public void deleteEntryById(long entryId) {
        Optional<Entry> entry = entryRepository.findById(entryId);

        if(entry.isPresent()) {
            logger.info("ENTRY WITH ID: " + entry.get().getEntryId() + " AT : " + Timestamp.valueOf(LocalDateTime.now()));
            entryRepository.delete(entry.get());
            budgetUtilities.updateBudget();
        }

    }

    @Override
    public void deleteEntryByIncomeId(long incomeId) {
        Entry entry = entryRepository.getEntryByIncomeId(incomeId);
        deleteEntryById(entry.getEntryId());

    }

    @Override
    public void deleteEntryByExpenseId(long expenseId) {
        Entry entry = entryRepository.getEntryByExpenseId(expenseId);
        deleteEntryById(entry.getEntryId());
    }


}
