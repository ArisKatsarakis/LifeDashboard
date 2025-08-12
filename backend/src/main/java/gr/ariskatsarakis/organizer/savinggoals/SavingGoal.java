package gr.ariskatsarakis.organizer.savinggoals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import gr.ariskatsarakis.organizer.spendinggoals.SpendingGoal;
import gr.ariskatsarakis.organizer.user.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_saving_goals")
public class SavingGoal {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long savingGoalId;
        private BigDecimal savingGoalMoney;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate startFrom;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate finishTo;

        @ManyToOne
        @JoinColumn(name = "user_id")
        @JsonIgnore
        private UserInfo userInfo;

        @OneToMany
        @JoinColumn(name = "saving_goal_id")
        private List<SpendingGoal> spendingGoals;

        public Long getSavingGoalId() {
                return savingGoalId;
        }

        public void setSavingGoalId(Long savingGoalId) {
                this.savingGoalId = savingGoalId;
        }

        public BigDecimal getSavingGoalMoney() {
                return savingGoalMoney;
        }

        public void setSavingGoalMoney(BigDecimal savingGoalMoney) {
                this.savingGoalMoney = savingGoalMoney;
        }

        public LocalDate getStartFrom() {
                return startFrom;
        }

        public void setStartFrom(LocalDate startFrom) {
                this.startFrom = startFrom;
        }

        public LocalDate getFinishTo() {
                return finishTo;
        }

        public void setFinishTo(LocalDate finishTo) {
                this.finishTo = finishTo;
        }

        public UserInfo getUserInfo() {
                return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
        }

        public List<SpendingGoal> getSpendingGoals() {
                return spendingGoals;
        }

        public void setSpendingGoals(List<SpendingGoal> spendingGoals) {
                this.spendingGoals = spendingGoals;
        }

}
