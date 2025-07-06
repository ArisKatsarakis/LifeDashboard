package gr.ariskatsarakis.organizer.expenses;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import org.slf4j.LoggerFactory;

/**
 * ExpenseService
 */
@Service
public class ExpenseService {

	private ExpenseRepository expenseRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	public List<Expense> fetchExpenses() {
		return expenseRepository.findAll();
	}

	public ExpenseDTO addExpense(Expense e) {
		Expense expense = expenseRepository.save(e);
		logger.info(String.format("Adding expense %s", expense.toString()));
		return fromExpenseToDTO(expense);
	}

	private ExpenseDTO fromExpenseToDTO(Expense expense) {
		ExpenseDTO dto = new ExpenseDTO();

		dto.setExpenseId(expense.getExpenseId());
		dto.setMoney(expense.getMoney());
		dto.setDateCreated(expense.getDateCreated());
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
			dtos.add(dto);
		}
		return dtos;
	}

	public Expense fromDTOToExpense(ExpenseDTO dto) {
		Expense e = new Expense();
		e.setExpenseId(dto.getExpenseId());
		e.setMoney(dto.getMoney());
		e.setDateCreated(dto.getDateCreated());
		return e;
	}
}
