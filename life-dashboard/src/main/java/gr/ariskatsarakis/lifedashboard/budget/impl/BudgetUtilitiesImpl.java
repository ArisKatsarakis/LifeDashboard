package gr.ariskatsarakis.lifedashboard.budget.impl;

import gr.ariskatsarakis.lifedashboard.budget.def.Budget;
import gr.ariskatsarakis.lifedashboard.budget.def.BudgetRepository;
import gr.ariskatsarakis.lifedashboard.budget.def.BudgetUtilities;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Transactional
public class BudgetUtilitiesImpl
implements BudgetUtilities {
    private static final Logger logger = LoggerFactory.getLogger(BudgetUtilitiesImpl.class);
    @Autowired
    private BudgetRepository budgetRepository;
    @Override
    public void updateBudget() {
        logger.info("UPDATING BUDGET "+ Timestamp.valueOf(LocalDateTime.now()));
        Pageable latest = PageRequest.of(0,1);
        Budget getLastBudget = budgetRepository.getLastBudget(latest).get(0);
        if (getLastBudget == null) {
            logger.info(" NO BUDGET FOUND");
        }
    }
}
