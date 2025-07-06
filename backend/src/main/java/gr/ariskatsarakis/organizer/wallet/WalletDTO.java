package gr.ariskatsarakis.organizer.wallet;

import java.math.BigDecimal;

/**
 * WalletDTO
 */
public class WalletDTO {
	private Long walletId;
	private String walletName;
	private BigDecimal totalPending;

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

}
