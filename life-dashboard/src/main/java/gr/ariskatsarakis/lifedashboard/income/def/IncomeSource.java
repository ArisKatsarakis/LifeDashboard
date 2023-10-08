package gr.ariskatsarakis.lifedashboard.income.def;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "income_sources")
public class IncomeSource {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long incomeSourceId;
    private String  Name;
    private Stability stabilityType;
    @OneToMany
    @JsonIgnore
    private Set<Income> incomeSet;

    private IncomeType incomeType;

    public IncomeSource() {
    }


    public IncomeSource(String name, Stability stabilityType, Set<Income> incomeSet, IncomeType incomeType) {
        Name = name;
        this.stabilityType = stabilityType;
        this.incomeSet = incomeSet;
        this.incomeType = incomeType;
    }

    public long getIncomeSourceId() {
        return incomeSourceId;
    }

    public void setIncomeSourceId(long incomeSourceId) {
        this.incomeSourceId = incomeSourceId;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Stability getStabilityType() {
        return stabilityType;
    }

    public void setStabilityType(Stability stabilityType) {
        this.stabilityType = stabilityType;
    }

    public Set<Income> getIncomeSet() {
        return incomeSet;
    }

    public void setIncomeSet(Set<Income> incomeSet) {
        this.incomeSet = incomeSet;
    }
}
