package gr.ariskatsarakis.lifedashboard.expense.beans;

public class ExpenseCritria {

    private  Integer year;

    private Integer month;

    private  Integer day;

    private ExpenseType expenseType;

    public ExpenseCritria(Integer year, Integer month, Integer day, ExpenseType expenseType) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.expenseType = expenseType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
