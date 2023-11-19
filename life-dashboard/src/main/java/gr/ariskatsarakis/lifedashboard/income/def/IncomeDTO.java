package gr.ariskatsarakis.lifedashboard.income.def;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO {
    private Long id;
    private Long incomeSourceId;
    private String description;
    private LocalDate dateCreated;
    private BigDecimal MoneyReceived;
    private String incomeSourceName;
}
