package gr.ariskatsarakis.organizer.expenses;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.user.UserInfoService;

/**
 * ExpenseService
 */
@Service
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
                e.setUserInfo(userInfoService.fetchUser());
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

}
