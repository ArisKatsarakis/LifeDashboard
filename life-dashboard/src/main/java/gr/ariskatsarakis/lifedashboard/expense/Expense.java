package gr.ariskatsarakis.lifedashboard.expense;

import jakarta.persistence.*;

@Entity
@Table( name = "life_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long expenseId;


}
