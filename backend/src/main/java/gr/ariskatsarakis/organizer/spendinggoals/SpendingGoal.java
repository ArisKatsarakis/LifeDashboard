package gr.ariskatsarakis.organizer.spendinggoals;

import java.math.BigDecimal;
import java.time.LocalDate;

import gr.ariskatsarakis.organizer.savinggoals.SavingGoal;
import gr.ariskatsarakis.organizer.user.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Spending Goal is auto calculated by the system.
 * It takes into account only one day.
 */
@Entity
@Table(name = "user_spending_goals")
public class SpendingGoal {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long spendingGoalId;
        private BigDecimal spendingMoney;
        private LocalDate spendingDay;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserInfo userInfo;

        @ManyToOne
        @JoinColumn(name = "saving_goal_id")
        private SavingGoal savingGoal;

        public Long getSpendingGoalId() {
                return spendingGoalId;
        }

        public void setSpendingGoalId(Long spendingGoalId) {
                this.spendingGoalId = spendingGoalId;
        }

        public BigDecimal getSpendingMoney() {
                return spendingMoney;
        }

        public void setSpendingMoney(BigDecimal spendingMoney) {
                this.spendingMoney = spendingMoney;
        }

        public LocalDate getSpendingDay() {
                return spendingDay;
        }

        public void setSpendingDay(LocalDate spendingDay) {
                this.spendingDay = spendingDay;
        }

        public UserInfo getUserInfo() {
                return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
        }

        public SavingGoal getSavingGoal() {
                return savingGoal;
        }

        public void setSavingGoal(SavingGoal savingGoal) {
                this.savingGoal = savingGoal;
        }

}
