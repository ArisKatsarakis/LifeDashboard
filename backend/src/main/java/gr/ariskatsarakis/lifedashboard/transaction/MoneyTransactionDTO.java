package gr.ariskatsarakis.lifedashboard.transaction;

import java.math.BigDecimal;

public class MoneyTransactionDTO {
	private BigDecimal money;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public MoneyTransactionDTO(MoneyTransaction moneyTransaction) {
		this.money = moneyTransaction.getMoney();
	}
}
