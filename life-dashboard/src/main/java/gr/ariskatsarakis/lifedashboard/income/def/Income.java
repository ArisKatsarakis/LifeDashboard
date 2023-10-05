package gr.ariskatsarakis.lifedashboard.income.def;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "life_incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long incomeId;
    private BigDecimal moneyReceived;
    private String description;
     @ManyToOne
     @JoinColumn(name = "expense_id", nullable = false)
    private IncomeSource incomeSource;

     private IncomeType incomeType;

    public Income() {
    }

    public Income(BigDecimal moneyReceived, String description, IncomeSource incomeSource, IncomeType incomeType) {
        this.moneyReceived = moneyReceived;
        this.description = description;
        this.incomeSource = incomeSource;
        this.incomeType = incomeType;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    public long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(long incomeId) {
        this.incomeId = incomeId;
    }

    public BigDecimal getMoneyReceived() {
        return moneyReceived;
    }

    public void setMoneyReceived(BigDecimal moneyReceived) {
        this.moneyReceived = moneyReceived;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IncomeSource getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(IncomeSource incomeSource) {
        this.incomeSource = incomeSource;
    }
}
