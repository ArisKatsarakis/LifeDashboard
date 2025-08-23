package gr.ariskatsarakis.organizer.statistics;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.expenses.ExpenseRepository;
import gr.ariskatsarakis.organizer.incomes.IncomeRespository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StatisticsService {

        public record StatisticsMonth(String name, Integer start, Integer end) {
        }

        private ExpenseRepository expenseRepository;
        private IncomeRespository incomeRespository;

        public StatisticsService(
                        ExpenseRepository expenseRepository,
                        IncomeRespository incomeRespository) {
                this.expenseRepository = expenseRepository;
                this.incomeRespository = incomeRespository;
        }

        public List<StatisticsMonth> fetchExpenseMoths() {
                List<StatisticsMonth> months = new ArrayList<>();

                List<Date> monthStrs = expenseRepository.findDistinctDates();
                List<LocalDate> localDateDates = monthStrs.stream().map(d -> {
                        return LocalDate.ofInstant(d.toInstant(), ZoneId.systemDefault());
                }).collect(Collectors.toList());
                for (LocalDate d : localDateDates) {
                        StatisticsMonth found = months
                                        .stream()
                                        .filter(m -> m.name() == d.getMonth().toString())
                                        .findFirst().orElse(null);
                        StatisticsMonth updatedFound = null;

                        if (found != null) {
                                updatedFound = new StatisticsMonth(
                                                d.getMonth().toString(),
                                                found.start() > d.getDayOfMonth()
                                                                ? d.getDayOfMonth()
                                                                : found.start(),
                                                found.end() < d.getDayOfMonth()
                                                                ? d.getDayOfMonth()
                                                                : found.end());
                                months.set(months.indexOf(found), updatedFound);
                                continue;
                        }
                        found = new StatisticsMonth(
                                        d.getMonth().toString(),
                                        d.getDayOfMonth(),
                                        d.getDayOfMonth());
                        months.add(found);
                }

                logger.info(months.toString());
                return months;
        }

        public List<StatisticsMonth> fetchIncomeMonths() {
                List<StatisticsMonth> months = new ArrayList<>();

                List<Date> monthStrs = incomeRespository.findDistinctDates();

                logger.info(monthStrs.toString());
                return months;
        }

        private Logger logger = LoggerFactory.getLogger(this.getClass());

}
