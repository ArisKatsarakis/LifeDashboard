package gr.ariskatsarakis.organizer.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.incomes.Income;
import gr.ariskatsarakis.organizer.savinggoals.SavingGoal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private int id;
        private String name;
        private String email;
        private String password;
        private String roles;

        @OneToMany
        @JoinColumn(name = "user_id")
        private List<Expense> expenses;

        @OneToMany
        @JoinColumn(name = "user_id")
        private List<Income> incomes;

        private BigDecimal totalMoneySpent = BigDecimal.ZERO;
        private BigDecimal totalMoneyPending = BigDecimal.ZERO;
        private BigDecimal totalMoneyReceived = BigDecimal.ZERO;

        @OneToMany
        @JoinColumn(name = "user_id")
        private List<SavingGoal> savingGoals;
}
