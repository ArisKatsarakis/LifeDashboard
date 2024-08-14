package gr.ariskatsarakis.lifedashboard.wallet;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import gr.ariskatsarakis.lifedashboard.expense.Expense;

/**
 * TestWalletService
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestWalletService {

  @Autowired
  private WalletService sut = new WalletService();

  @BeforeAll
  void setup() {
    List<Wallet> wallets = new ArrayList<>();
    sut.walletRepository = Mockito.mock(WalletRepository.class);
    Mockito.when(sut.walletRepository.findAll()).thenReturn(wallets);
  }

  @Test
  void createNewWallet_test() {

    assertThat(sut.walletRepository.findAll().isEmpty()).isEqualTo(true);

    Expense e = new Expense();
    e.setTimestamp(Timestamp.valueOf(LocalDateTime.now().minusDays(2)));
    e.setMoney(BigDecimal.valueOf(20l));
    BigDecimal expected = BigDecimal.ZERO.subtract(e.getMoney());
    Wallet wallet = this.sut.addNewExpenseAtWallet(e);
    assertThat(wallet.getMoneyNow()).isEqualTo(expected);
    assertThat(wallet.getLastExpenseDate()).isEqualTo(e.getTimestamp());
  }

}
