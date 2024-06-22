package gr.ariskatsarakis.lifedashboard.expense;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

/**
 * ExpenseType
 */
@Entity
public class ExpenseType {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long expenseTypeId;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "expenseType")
  private List<Expense> expense;
  private String expenseTypeName;
  private BigDecimal expensesSum;

  public ExpenseType() {

  }

  public List<Expense> getExpense() {
    return expense;
  }

  public Long getExpenseTypeId() {
    return expenseTypeId;
  }

  public String getExpenseTypeName() {
    return expenseTypeName;
  }

  public void setExpense(List<Expense> expense) {
    this.expense = expense;
  }

  public void setExpenseTypeId(Long expenseTypeId) {
    this.expenseTypeId = expenseTypeId;
  }

  public void setExpenseTypeName(String expenseTypeName) {
    this.expenseTypeName = expenseTypeName;
  }

  public BigDecimal getExpensesSum() {
    return expensesSum;
  }

  public void setExpensesSum(BigDecimal expensesSum) {
    this.expensesSum = expensesSum;
  }

}
