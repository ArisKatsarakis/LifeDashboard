package gr.ariskatsarakis.organizer.incomes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.ariskatsarakis.organizer.user.UserInfo;

/**
 * IncomeRespository
 */
@Repository
public interface IncomeRespository extends JpaRepository<Income, Long> {

        List<Income> findByUserInfo(UserInfo userInfo);

        List<Income> findByUserInfoAndIncomeCategory(UserInfo userInfo, IncomeCategory category);
}
