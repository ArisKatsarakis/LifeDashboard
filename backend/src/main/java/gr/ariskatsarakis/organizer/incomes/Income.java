package gr.ariskatsarakis.organizer.incomes;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import gr.ariskatsarakis.organizer.user.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Income
 */
@Entity
@Table(name = "user_incomes")
public class Income {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long incomeId;
        private BigDecimal money;
        @JsonFormat(pattern = "dd/MM/yy")
        private Date dateCreated;
        private IncomeCategory incomeCategory;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserInfo userInfo;

        public Long getIncomeId() {
                return incomeId;
        }

        public void setIncomeId(Long incomeId) {
                this.incomeId = incomeId;
        }

        public BigDecimal getMoney() {
                return money;
        }

        public void setMoney(BigDecimal money) {
                this.money = money;
        }

        public Date getDateCreated() {
                return dateCreated;
        }

        public void setDateCreated(Date dateCreated) {
                this.dateCreated = dateCreated;
        }

        public IncomeCategory getIncomeCategory() {
                return incomeCategory;
        }

        public void setIncomeCategory(IncomeCategory incomeCategory) {
                this.incomeCategory = incomeCategory;
        }

        public UserInfo getUserInfo() {
                return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
        }

}
