package gr.ariskatsarakis.lifedashboard.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WalletController
 */
@RestController
@RequestMapping("/api/v1")
public class WalletController {

  @Autowired
  private WalletService walletService;

  @GetMapping("/wallet")
  public ResponseEntity<List<Wallet>> getAllWallets() {
    return walletService.getAll();
  }

  @GetMapping("/last-wallet")
  public ResponseEntity<Wallet> getLastWallet() {
    return walletService.getLastWallet();
  }

}
