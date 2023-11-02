package gr.ariskatsarakis.lifedashboard.income.def;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "life_incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long incomeId;
    private BigDecimal moneyReceived;
    private String description;
    private LocalDate dateCreated;

    private Long sourceId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "income_source_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private IncomeSource incomeSource;

    public Income() {
    }

    public Income(BigDecimal moneyReceived, String description, IncomeSource incomeSource) {
        this.moneyReceived = moneyReceived;
        this.description = description;
        this.dateCreated = LocalDate.now();
        this.incomeSource = incomeSource;
        this.sourceId = incomeSource.getIncomeSourceId();
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long incomeSourceId) {
        this.sourceId = incomeSourceId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + incomeId +
                ", moneyReceived=" + moneyReceived +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", incomeSource=" + incomeSource.getIncomeSourceId() +
                '}';
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
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
