package gr.ariskatsarakis.organizer.expenses;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.user.UserInfo;
import gr.ariskatsarakis.organizer.user.UserInfoService;
import jakarta.transaction.Transactional;

/**
 * ExpenseService
 */
@Service
@Transactional
public class ExpenseService {

        private ExpenseRepository expenseRepository;
        private UserInfoService userInfoService;

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        public ExpenseService(ExpenseRepository expenseRepository, UserInfoService userInfoService) {
                this.expenseRepository = expenseRepository;
                this.userInfoService = userInfoService;
        }

        public List<Expense> fetchExpenses() {
                return expenseRepository.findByUserInfo(userInfoService.fetchUser());
        }

        public ExpenseDTO addExpense(Expense e) {
                UserInfo userInfo = userInfoService.fetchUser();
                if (userInfo == null) {
                        throw new UsernameNotFoundException("UserName not found");
                }
                e.setUserInfo(userInfo);
                BigDecimal totalSpent = userInfo.getTotalMoneySpent();
                totalSpent = totalSpent != null ? totalSpent.add(e.getMoney()) : e.getMoney();
                BigDecimal totalPending = userInfo.getTotalMoneyPending() != null
                                ? userInfo.getTotalMoneyPending().subtract(e.getMoney())
                                : BigDecimal.ZERO.subtract(e.getMoney());
                userInfo.setTotalMoneySpent(totalSpent);
                userInfo.setTotalMoneyPending(totalPending);
                userInfoService.updateUser(userInfo);
                Expense expense = expenseRepository.save(e);
                return fromExpenseToDTO(expense);
        }

        public ExpenseDTO fromExpenseToDTO(Expense expense) {
                ExpenseDTO dto = new ExpenseDTO();

                dto.setExpenseId(expense.getExpenseId());
                dto.setMoney(expense.getMoney());
                dto.setDateCreated(expense.getDateCreated());
                dto.setName(expense.getName());
                dto.setCategory(expense.getCategory().toString());
                return dto;
        }

        public List<ExpenseDTO> toListExpenseDtos(List<Expense> expenses) {
                if (expenses.size() == 0) {
                        return new ArrayList<>();
                }
                List<ExpenseDTO> dtos = new ArrayList<>();
                for (Expense e : expenses) {
                        ExpenseDTO dto = new ExpenseDTO();
                        dto.setExpenseId(e.getExpenseId());
                        dto.setMoney(e.getMoney());
                        dto.setDateCreated(e.getDateCreated());
                        dto.setName(e.getName());
                        dto.setCategory(e.getCategory().toString());
                        dtos.add(dto);
                }
                return dtos;
        }

        public Expense fromDTOToExpense(ExpenseDTO dto) {
                Expense e = new Expense();
                e.setExpenseId(dto.getExpenseId());
                e.setMoney(dto.getMoney());
                e.setDateCreated(dto.getDateCreated());
                e.setName(dto.getName());
                e.setCategory(ExpenseCategory.valueOf(dto.getCategory()));
                return e;
        }

        public List<ExpenseDTO> fetchExpensesByCategories(ExpenseCategory category) {
                List<ExpenseDTO> dtos = new ArrayList<>();
                UserInfo userInfo = userInfoService.fetchUser();

                if (userInfo == null) {
                        throw new UsernameNotFoundException("username not found");

                }

                List<Expense> expenses = expenseRepository.findByUserInfoAndCategory(userInfo, category);
                logger.info(Arrays.deepToString(expenses.toArray()));
                for (Expense e : expenses) {
                        dtos.add(fromExpenseToDTO(e));
                }
                return dtos;
        }

}
