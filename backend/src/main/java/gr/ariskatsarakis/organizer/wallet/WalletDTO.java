package gr.ariskatsarakis.organizer.wallet;

import java.math.BigDecimal;
import java.util.List;

import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.incomes.Income;

/**
 * WalletDTO
 */
public class WalletDTO {
	private Long walletId;
	private String walletName;
	private BigDecimal totalPending;
	private List<Expense> expenses;
	private List<Income> incomes;
	private BigDecimal totalExpenses;
	private BigDecimal totalIncomes;

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public BigDecimal getTotalPending() {
		return totalPending;
	}

	public void setTotalPending(BigDecimal totalPending) {
		this.totalPending = totalPending;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	public BigDecimal getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(BigDecimal totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public BigDecimal getTotalIncomes() {
		return totalIncomes;
	}

	public void setTotalIncomes(BigDecimal totalIncomes) {
		this.totalIncomes = totalIncomes;
	}

}
