package gr.ariskatsarakis.lifedashboard.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * WalletRepository
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

  @Query("Select w from Wallet w where w.dateCreated = (select max(w1.dateCreated) from Wallet w1) ")
  Wallet getLastAddedWallet();

}
