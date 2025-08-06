package gr.ariskatsarakis.organizer.incomes;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * IncomeDTO
 */
public class IncomeDTO {

        private Long incomeId;
        private BigDecimal money;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date dateCreated;
        private String category;

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

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

}
