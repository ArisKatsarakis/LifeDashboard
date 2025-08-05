package gr.ariskatsarakis.organizer.wallet;

import java.math.BigDecimal;
import java.util.List;

import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.incomes.Income;
import gr.ariskatsarakis.organizer.user.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Wallet
 */
@Entity
@Table(name = "user_wallet")
public class Wallet {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long walletId;
        @OneToMany
        @JoinColumn(name = "wallet_id")
        private List<Expense> expenses;

        @OneToMany
        @JoinColumn(name = "wallet_id")
        private List<Income> incomes;
        private BigDecimal totalExpenses;
        private BigDecimal totalIncomes;
        private BigDecimal totalPending;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserInfo userInfo;

        public BigDecimal getTotalPending() {
                return totalPending;
        }

        public void setTotalPending(BigDecimal totalPending) {
                this.totalPending = totalPending;
        }

        private String walletName;

        public Long getWalletId() {
                return walletId;
        }

        public void setWalletId(Long walletId) {
                this.walletId = walletId;
        }

        public List<Expense> getExpenses() {
                return expenses;
        }

        public void setExpenses(List<Expense> expenses) {
                this.expenses = expenses;
        }

        public BigDecimal getTotalExpenses() {
                return totalExpenses;
        }

        public void setTotalExpenses(BigDecimal totalExpenses) {
                this.totalExpenses = totalExpenses;
        }

        public String getWalletName() {
                return walletName;
        }

        public void setWalletName(String walletName) {
                this.walletName = walletName;
        }

        public List<Income> getIncomes() {
                return incomes;
        }

        public void setIncomes(List<Income> incomes) {
                this.incomes = incomes;
        }

        public BigDecimal getTotalIncomes() {
                return totalIncomes;
        }

        public void setTotalIncomes(BigDecimal totalIncomes) {
                this.totalIncomes = totalIncomes;
        }

        public UserInfo getUserInfo() {
                return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
                this.userInfo = userInfo;
        }

}
