package gr.ariskatsarakis.organizer.incomes;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import gr.ariskatsarakis.organizer.wallet.Wallet;
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

        @ManyToOne
        @JoinColumn(name = "wallet_id", nullable = true)
        @JsonIgnore
        private Wallet wallet;
        private IncomeCategory incomeCategory;

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

        public Wallet getWallet() {
                return wallet;
        }

        public void setWallet(Wallet wallet) {
                this.wallet = wallet;
        }

        public IncomeCategory getIncomeCategory() {
                return incomeCategory;
        }

        public void setIncomeCategory(IncomeCategory incomeCategory) {
                this.incomeCategory = incomeCategory;
        }

}
