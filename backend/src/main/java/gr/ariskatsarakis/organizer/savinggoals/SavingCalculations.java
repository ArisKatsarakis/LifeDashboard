package gr.ariskatsarakis.organizer.savinggoals;

import java.math.BigDecimal;

public class SavingCalculations {
        private Long daysLeft;
        private BigDecimal dailySpending;

        public Long getDaysLeft() {
                return daysLeft;
        }

        public void setDaysLeft(Long daysLeft) {
                this.daysLeft = daysLeft;
        }

        public BigDecimal getDailySpending() {
                return dailySpending;
        }

        public void setDailySpending(BigDecimal dailySpending) {
                this.dailySpending = dailySpending;
        }

}
