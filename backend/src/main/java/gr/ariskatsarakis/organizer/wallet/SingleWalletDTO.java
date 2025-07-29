package gr.ariskatsarakis.organizer.wallet;

import java.math.BigDecimal;

public class SingleWalletDTO {
	private Long walletId;
	private String walletName;
	private BigDecimal TotalPending;
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
		return TotalPending;
	}

	public void setTotalPending(BigDecimal totalPending) {
		TotalPending = totalPending;
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
