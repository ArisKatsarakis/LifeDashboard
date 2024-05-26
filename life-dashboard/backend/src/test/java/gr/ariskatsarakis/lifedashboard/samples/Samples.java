package gr.ariskatsarakis.lifedashboard.samples;

import java.math.BigDecimal;
import java.util.Random;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import gr.ariskatsarakis.lifedashboard.expense.Expense;

/**
 * Samples
 */
public class Samples {

  public static Expense sampleExpense() {
    Expense e = new Expense();
    Random r = new Random();

    e.setMoney(BigDecimal.valueOf(r.nextLong(1000L)));
    e.setExpenseId(r.nextLong(10L));
    e.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

    return e;
  }
}
