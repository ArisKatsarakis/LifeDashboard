package gr.ariskatsarakis.organizer.expenses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.ariskatsarakis.organizer.wallet.Wallet;

/**
 * ExpenseRepository
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByWallet(Wallet wallet);
}
