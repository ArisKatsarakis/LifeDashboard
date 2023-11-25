package gr.ariskatsarakis.lifedashboard.budget.impl;

import gr.ariskatsarakis.lifedashboard.budget.def.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class BudgetUtilitiesImpl
implements BudgetUtilities {
    private static final Logger logger = LoggerFactory.getLogger(BudgetUtilitiesImpl.class);
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    EntryRepository entryRepository;

    @Override
    public void updateBudget(Entry entry ) {
        logger.info("UPDATING BUDGET "+ Timestamp.valueOf(LocalDateTime.now()));
        Pageable latest = PageRequest.of(0,1);
        List<Budget> lastBudget = budgetRepository.getLastBudget(latest);
        if (lastBudget.isEmpty()) {
            logger.info("NO BUDGET FOUND");
            logger.info("Budget initializing");
            Budget firstBudget = createInitialBudget();
            budgetRepository.save(firstBudget);
        }else {
            Budget currentBudget = createBudgetFromLastEntry(lastBudget.get(0), entry);
        }

    }



    @Override
    public Budget createInitialBudget() {
        Budget budget = new Budget();
        budget.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        List<Entry>  allEntries = entryRepository.findAll();
        Collections.sort(allEntries, (entry1, entry2) -> entry1.getDateInserted().compareTo(entry2.getDateInserted()));
        BigDecimal sum = allEntries.stream().map (
                entry  -> {
                    if (entry.getExpenseId() == 0l )  {
                            budget.setLastIncomeDate(
                                    entry.getDateInserted()
                            );
                            return entry.getMoney();
                    } else {
                        budget.setLastExpenseDate(
                                entry.getDateInserted()
                        );
                       return entry.getMoney().negate();
                    }
                }
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
        logger.info("Budget Money are : " + sum);
        logger.info("Last Income " + budget.getLastIncomeDate());
        logger.info("Last Expense : " + budget.getLastExpenseDate());
        budget.setWalletMoney(sum);
        return budget;
    }

    @Override
    public Budget createBudgetFromLastEntry(Budget lastBudget, Entry entry) {
        Budget updatedBudget = new Budget();
        if (entry.getExpenseId() ==  0L ) {
            updatedBudget.setLastIncomeDate(entry.getDateInserted());
            updatedBudget.setWalletMoney(lastBudget.getWalletMoney().add(entry.getMoney()));
            updatedBudget.setLastExpenseDate(lastBudget.getLastExpenseDate());
            updatedBudget.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        } else {
            updatedBudget.setLastIncomeDate(lastBudget.getLastIncomeDate());
            updatedBudget.setWalletMoney(lastBudget.getWalletMoney().subtract(entry.getMoney()));
            updatedBudget.setLastExpenseDate(entry.getDateInserted());
            updatedBudget.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        }
        return updatedBudget;
    }


    @Override
    public void refreshBudget() {

    }
}
