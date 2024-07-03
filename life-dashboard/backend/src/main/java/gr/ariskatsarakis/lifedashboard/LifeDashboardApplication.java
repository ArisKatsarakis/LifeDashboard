package gr.ariskatsarakis.lifedashboard;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import gr.ariskatsarakis.lifedashboard.expense.Expense;
import gr.ariskatsarakis.lifedashboard.expense.ExpenseRepository;
import gr.ariskatsarakis.lifedashboard.expense.ExpenseType;
import gr.ariskatsarakis.lifedashboard.expense.ExpenseTypeRepository;

@SpringBootApplication
public class LifeDashboardApplication {

  public static void main(String[] args) {
    SpringApplication.run(LifeDashboardApplication.class, args);

  }

  @Component
  class DatabaseFill implements CommandLineRunner {
    @Autowired
    ExpenseTypeRepository expenseTypeRepository;

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public void run(String... args) throws Exception {
      ExpenseType expenseType = new ExpenseType();
      expenseType.setExpenseTypeName("Needs");
      expenseType = expenseTypeRepository.save(expenseType);
      Expense expense = new Expense();
      expense.setMoney(BigDecimal.valueOf(20L));
      expense.setExpenseType(expenseType);
      expense.setName("Takakia");
      expense.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
      expenseRepository.save(expense);
      expenseType.setExpensesSum(expense.getMoney());
      List<Expense> expenses = new ArrayList<>();
      expenses.add(expense);
      expenseType.setExpense(expenses);
      expenseTypeRepository.save(expenseType);
    }
  }

}
