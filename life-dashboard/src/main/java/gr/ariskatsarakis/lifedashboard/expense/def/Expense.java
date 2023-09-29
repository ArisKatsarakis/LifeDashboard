package gr.ariskatsarakis.lifedashboard.expense.def;

import jakarta.persistence.*;

@Entity
@Table( name = "life_expenses")
public class Expense {

    /**
     * Todo
     * Add these fields
     * date_created.
     * Enum Type : UTILITIES, FUN, SAVINGS, DEBTS
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long expenseId;
    private String expenseName;

    public Expense( String expenseName) {
        this.expenseName = expenseName;
    }

    public Expense() {

    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", expenseName='" + expenseName + '\'' +
                '}';
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
}
