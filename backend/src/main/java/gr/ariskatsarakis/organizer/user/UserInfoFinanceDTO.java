package gr.ariskatsarakis.organizer.user;

import java.math.BigDecimal;

public class UserInfoFinanceDTO {

        private BigDecimal totalMoneySpent;
        private BigDecimal totalMoneyPending;
        private BigDecimal totalMoneyReceived;

        public BigDecimal getTotalMoneySpent() {
                return totalMoneySpent;
        }

        public void setTotalMoneySpent(BigDecimal totalMoneySpent) {
                this.totalMoneySpent = totalMoneySpent;
        }

        public BigDecimal getTotalMoneyPending() {
                return totalMoneyPending;
        }

        public void setTotalMoneyPending(BigDecimal totalMoneyPending) {
                this.totalMoneyPending = totalMoneyPending;
        }

        public BigDecimal getTotalMoneyReceived() {
                return totalMoneyReceived;
        }

        public void setTotalMoneyReceived(BigDecimal totalMoneyReceived) {
                this.totalMoneyReceived = totalMoneyReceived;
        }

}
