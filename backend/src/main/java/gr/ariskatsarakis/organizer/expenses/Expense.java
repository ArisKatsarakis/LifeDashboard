package gr.ariskatsarakis.organizer.expenses;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

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

@Service
@Entity
@Table(name = "user_expenses")
public class Expense {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long expenseId;
        private BigDecimal money;
        @JsonFormat(pattern = "dd/MM/yy")
        private Date dateCreated;
        @ManyToOne
        @JoinColumn(name = "wallet_id", nullable = true)
        @JsonIgnore
        private Wallet wallet;
        private String name;
        private ExpenseCategory category;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Long getExpenseId() {
                return expenseId;
        }

        public void setExpenseId(Long expenseId) {
                this.expenseId = expenseId;
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

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                sb.append(String.format("\n\texpenseId:%d,", this.expenseId.longValue()));
                sb.append(String.format("\n\tmoney:%d,", this.money.longValue()));
                sb.append(String.format("\n\tdateCreated:%s,", this.dateCreated.toString()));
                sb.append("}");
                return sb.toString();
        }

        public ExpenseCategory getCategory() {
                return category;
        }

        public void setCategory(ExpenseCategory category) {
                this.category = category;
        }

}
