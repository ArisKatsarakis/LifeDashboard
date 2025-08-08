package gr.ariskatsarakis.organizer.expenses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.ariskatsarakis.organizer.user.UserInfo;

/**
 * ExpenseRepository
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

        List<Expense> findByUserInfo(UserInfo userInfo);

        List<Expense> findByCategory(ExpenseCategory categoyr);

        List<Expense> findByUserInfoAndCategory(UserInfo userInfo, ExpenseCategory category);

}
