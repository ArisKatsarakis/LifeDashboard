package gr.ariskatsarakis.lifedashboard.expense.def;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name = "life_expenses")
public class Expense {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long expenseId;
    private String expenseName;

    private ExpenseType expenseType;

    private LocalDate dateCreated;

    private BigDecimal moneySpent;


    public Expense(String expenseName, ExpenseType expenseType, LocalDate dateCreated, BigDecimal moneySpent) {
        this.expenseName = expenseName;
        this.expenseType = expenseType;
        this.dateCreated = dateCreated;
        this.moneySpent = moneySpent;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", expenseName='" + expenseName + '\'' +
                ", expenseType=" + expenseType +
                ", dateCreated=" + dateCreated +
                ", moneySpent=" + moneySpent +
                '}';
    }

    public Expense() {

    }


    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(BigDecimal moneySpent) {
        this.moneySpent = moneySpent;
    }
}
