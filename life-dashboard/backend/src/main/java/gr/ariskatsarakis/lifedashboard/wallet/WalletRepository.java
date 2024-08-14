package gr.ariskatsarakis.lifedashboard.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * WalletRepository
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
