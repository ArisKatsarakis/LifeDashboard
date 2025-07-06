package gr.ariskatsarakis.organizer.expenses;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ExpenseDTO
 */
public class ExpenseDTO {
	private Long expenseId;
	private BigDecimal money;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateCreated;

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
