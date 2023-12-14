package gr.ariskatsarakis.lifedashboard;

import gr.ariskatsarakis.lifedashboard.expense.beans.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.def.Expense;
import gr.ariskatsarakis.lifedashboard.expense.def.ExpenseService;
import gr.ariskatsarakis.lifedashboard.income.def.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class LifeDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeDashboardApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(IncomeService incomeService, IncomeSourceService incomeSourceService, ExpenseService expenseService) {
        return (args) -> {
            if (incomeService.getAllIncomesToDtos().isEmpty()) {
                IncomeSource incomeSource = new IncomeSource();
                incomeSource.setIncomeType(IncomeType.CODING);
                incomeSource.setStabilityType(Stability.STABLE);
                incomeSource.setName("CODING");
                incomeSource = incomeSourceService.addIncomeSource(incomeSource);
                Income income = new Income();
                income.setIncomeSource(incomeSource);
                income.setDescription("Income 1");
                income.setMoneyReceived(BigDecimal.valueOf(100L));
                income.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
                incomeService.addIncome(income, incomeSource.getIncomeSourceId());
                Expense expense = new Expense();
                expense.setExpenseName("Expense 1");
                expense.setDateCreated(LocalDate.now());
                expense.setExpenseType(ExpenseType.DEBTS);
                expense.setMoneySpent(BigDecimal.valueOf(30L));
                expenseService.addExpense(expense);
            }


        };
    }

}
