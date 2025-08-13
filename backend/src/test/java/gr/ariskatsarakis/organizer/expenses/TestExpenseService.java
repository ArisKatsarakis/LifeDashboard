package gr.ariskatsarakis.organizer.expenses;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import gr.ariskatsarakis.organizer.spendinggoals.SpendingGoalRepository;
import gr.ariskatsarakis.organizer.spendinggoals.SpendingGoalService;
import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestExpenseService {
        @InjectMocks
        private ExpenseService expenseService;

        @Mock
        private ExpenseRepository expenseRepository;
        @Mock
        private UserInfoService userInfoService;

        @Autowired
        private SpendingGoalRepository spendingGoalRepository;
        @Autowired
        private SpendingGoalService spendingGoalService;

        @Test
        @Transactional
        @Rollback
        public void Test_addExpensesAndUpdateSpendingGoals() {

                /*** TODO fix the date error */
                // this.expenseService = new ExpenseService(expenseRepository, userInfoService,
                // this.spendingGoalService);
                // UserInfo userInfo = new UserInfo();
                // userInfo.setId(1);
                // userInfo.setEmail("aris@email.com");
                //
                // when(userInfoService.fetchUser()).thenReturn(userInfo);
                // when(expenseRepository.save(Mockito.any(Expense.class))).then(i -> {
                // Expense e = i.getArgument(0);
                // e.setExpenseId(0l);
                // return e;
                /*** TODO fix the date error **/
                //
                // Expense expense = new Expense();
                // expense.setDateCreated(Date.valueOf(LocalDate.now()));
                // expense.setMoney(BigDecimal.TEN);
                // expense.setCategory(ExpenseCategory.FOOD);
                // ExpenseDTO eDto = expenseService.addExpense(expense);
                // List<SpendingGoal> spendingGoals =
                // spendingGoalRepository.findBySpendingDay(LocalDate.now());
                // System.out.println(spendingGoals);
                // assertTrue(eDto != null);
                // for (int i = 0; i < spendingGoals.size(); i++) {
                // assertEquals(BigDecimal.valueOf(10.00),
                // spendingGoals.get(i).getDaySpented());
                // }
                //
        }

}
