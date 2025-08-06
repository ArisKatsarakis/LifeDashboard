package gr.ariskatsarakis.organizer.wallet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.ariskatsarakis.organizer.user.UserInfo;

/**
 * WalletRepository
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

        List<Wallet> findByUserInfo(UserInfo userInfo);
}
