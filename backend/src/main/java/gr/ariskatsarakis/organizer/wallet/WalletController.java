package gr.ariskatsarakis.organizer.wallet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.expenses.ExpenseDTO;
import gr.ariskatsarakis.organizer.expenses.ExpenseService;

/**
 * WalletController
 */
@RestController
@RequestMapping("api/v1/wallet")
@CrossOrigin
public class WalletController {

	private WalletService walletService;
	private ExpenseService expenseService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public WalletController(WalletService walletService, ExpenseService expenseService) {
		this.walletService = walletService;
		this.expenseService = expenseService;
	}

	@GetMapping
	public ResponseEntity<List<WalletDTO>> getWallets() {
		return new ResponseEntity<>(walletService.fetchWallets(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<WalletDTO> addWallet(@RequestBody WalletDTO walletDTO) {
		return new ResponseEntity<>(walletService.addWallet(walletDTO), HttpStatus.OK);
	}

	@GetMapping("/{walletId}/expenses")
	public ResponseEntity<List<ExpenseDTO>> fetchWalletExpenses(@PathVariable Long walletId) {
		Wallet wallet = walletService.fetchWallet(walletId);
		if (wallet == null) {
			new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		logger.info(String.format("Quering expenses for walletId: %d", walletId));
		List<Expense> expenses = expenseService.fetchByWallet(wallet);
		return new ResponseEntity<>(expenseService.toListExpenseDtos(expenses), HttpStatus.OK);
	}

	@PostMapping("/{walletId}/expenses")
	public ResponseEntity<ExpenseDTO> addWalletExpenses(@PathVariable Long walletId,
			@RequestBody ExpenseDTO expense) {
		Wallet wallet = walletService.fetchWallet(walletId);
		if (wallet == null) {
			new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(walletService.addWalletExpense(wallet, expense), HttpStatus.OK);
	}

}
