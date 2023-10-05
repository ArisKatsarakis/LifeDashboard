package gr.ariskatsarakis.lifedashboard.income.def;

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
    private Set<Income> incomeSet;

    public IncomeSource() {
    }

    public IncomeSource(String name, Stability stabilityType, Set<Income> incomeSet) {
        Name = name;
        this.stabilityType = stabilityType;
        this.incomeSet = incomeSet;
    }

    @Override
    public String toString() {
        return "IncomeSource{" +
                "incomeSourceId=" + incomeSourceId +
                ", Name='" + Name + '\'' +
                ", stabilityType=" + stabilityType +
                ", incomeSet=" + incomeSet +
                '}';
    }

    public long getIncomeSourceId() {
        return incomeSourceId;
    }

    public void setIncomeSourceId(long incomeSourceId) {
        this.incomeSourceId = incomeSourceId;
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
