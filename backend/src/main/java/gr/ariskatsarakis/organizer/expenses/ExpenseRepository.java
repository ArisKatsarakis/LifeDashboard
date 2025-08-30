package gr.ariskatsarakis.organizer.expenses;

import gr.ariskatsarakis.organizer.user.UserInfo;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * ExpenseRepository
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  List<Expense> findByUserInfo(UserInfo userInfo);

  List<Expense> findByCategory(ExpenseCategory categoyr);

  List<Expense> findByUserInfoAndCategory(UserInfo userInfo,
                                          ExpenseCategory category);

  @Query("select distinct( e.dateCreated ) from Expense e")
  List<Date> findDistinctDates();

  List<Expense> findByUserInfoOrderByDateCreatedDesc(UserInfo fetchUser,
                                                     PageRequest of);
}
