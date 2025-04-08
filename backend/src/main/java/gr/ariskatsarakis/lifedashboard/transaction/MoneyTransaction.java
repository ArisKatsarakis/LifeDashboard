package gr.ariskatsarakis.lifedashboard.transaction;

import java.math.BigDecimal;

import gr.ariskatsarakis.lifedashboard.user.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.ToString;

@Entity
@Table(name = "money_transactions")
@ToString
public class MoneyTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long transactionId;
	private BigDecimal money;
	@ManyToOne
	private AppUser appUser;

	public MoneyTransaction() {
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
