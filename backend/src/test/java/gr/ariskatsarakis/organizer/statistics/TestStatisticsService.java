package gr.ariskatsarakis.organizer.statistics;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import gr.ariskatsarakis.organizer.expenses.ExpenseRepository;
import gr.ariskatsarakis.organizer.incomes.IncomeRespository;
import gr.ariskatsarakis.organizer.statistics.StatisticsService.StatisticsMonth;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestStatisticsService {

        @Mock
        private IncomeRespository incomeRespository;
        @Mock
        private ExpenseRepository expenseRepository;

        @InjectMocks
        private StatisticsService sut;

        @Test
        @Rollback
        public void Test_fetchExpenseMonths() {
                when(expenseRepository.findDistinctDates()).thenReturn(
                                new ArrayList<>() {
                                        {
                                                add(new Date(2025, 7, 1));
                                                add(new Date(2025, 7, 10));
                                                add(new Date(2025, 7, 31));
                                        }
                                });

                List<StatisticsMonth> mothsFetched = sut.fetchExpenseMoths();
                assertTrue(mothsFetched.size() == 1);
                assertTrue(mothsFetched.get(0).start() == 1);
                assertTrue(mothsFetched.get(0).end() == 31);

                when(expenseRepository.findDistinctDates()).thenReturn(
                                new ArrayList<>() {
                                        {
                                                add(new Date(2025, 7, 1));
                                                add(new Date(2025, 7, 10));
                                                add(new Date(2025, 8, 10));
                                        }
                                });
                mothsFetched = sut.fetchExpenseMoths();
                assertTrue(mothsFetched.size() == 2);
                assertTrue(mothsFetched.get(0).start() == 1);
                assertTrue(mothsFetched.get(0).end() == 10);
                assertTrue(mothsFetched.get(1).start() == 10);
                assertTrue(mothsFetched.get(1).end() == 10);
        }

}
