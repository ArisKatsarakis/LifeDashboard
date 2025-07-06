package gr.ariskatsarakis.organizer.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * WalletRepository
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
