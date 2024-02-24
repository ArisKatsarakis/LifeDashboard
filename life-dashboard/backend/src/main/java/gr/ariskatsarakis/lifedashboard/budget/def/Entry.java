package gr.ariskatsarakis.lifedashboard.budget.def;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "money_entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long entryId;
    private BigDecimal money;
    private String entryType;
    private Timestamp dateInserted;

    private long incomeId;

    private long expenseId;

    public Entry() {
    }

    public Entry(long entryId, BigDecimal money, String entryType, Timestamp dateInserted, long incomeId, long expenseId) {
        this.entryId = entryId;
        this.money = money;
        this.entryType = entryType;
        this.dateInserted = dateInserted;
        this.incomeId = incomeId;
        this.expenseId = expenseId;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryId=" + entryId +
                ", money=" + money +
                ", entryType='" + entryType + '\'' +
                ", dateInserted=" + dateInserted +
                ", incomeId=" + incomeId +
                ", expenseId=" + expenseId +
                '}';
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public Timestamp getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Timestamp dateInserted) {
        this.dateInserted = dateInserted;
    }

    public long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(long incomeId) {
        this.incomeId = incomeId;
    }

    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }
}

