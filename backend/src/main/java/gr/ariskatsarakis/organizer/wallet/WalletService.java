package gr.ariskatsarakis.organizer.wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.expenses.ExpenseDTO;
import gr.ariskatsarakis.organizer.expenses.ExpenseService;

/**
 * WalletService
 */
@Service
public class WalletService {

	private WalletRepository walletRepository;

	private ExpenseService expenseService;

	public WalletService(WalletRepository walletRepository, ExpenseService expenseService) {
		this.walletRepository = walletRepository;
		this.expenseService = expenseService;
	}

	public WalletDTO addWallet(WalletDTO wallet) {
		Wallet w = fromDTOToWallet(wallet);
		if (w.getTotalPending() == null) {
			w.setTotalPending(BigDecimal.ZERO);
		}
		walletRepository.save(w);
		return fromWalletToDTO(w);

	}

	public List<WalletDTO> fetchWallets() {

		List<Wallet> wallets = walletRepository.findAll();
		return fromWalletsToList(wallets);

	}

	private List<WalletDTO> fromWalletsToList(List<Wallet> wallets) {
		List<WalletDTO> walletDTOs = new ArrayList<>();
		for (Wallet w : wallets) {
			walletDTOs.add(fromWalletToDTO(w));
		}
		return walletDTOs;
	}

	private WalletDTO fromWalletToDTO(Wallet w) {
		WalletDTO dto = new WalletDTO();
		dto.setWalletId(w.getWalletId());
		dto.setWalletName(w.getWalletName());
		dto.setTotalPending(w.getTotalPending());
		return dto;
	}

	private Wallet fromDTOToWallet(WalletDTO wallet) {
		Wallet w = new Wallet();
		w.setWalletId(wallet.getWalletId());
		w.setWalletName(wallet.getWalletName());
		w.setTotalPending(w.getTotalPending());
		return w;
	}

	public Wallet fetchWallet(Long walletId) {
		Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
		if (optionalWallet.isPresent()) {
			return optionalWallet.get();
		}
		return null;

	}

	public ExpenseDTO addWalletExpense(Wallet wallet, ExpenseDTO expense) {
		Expense e = expenseService.fromDTOToExpense(expense);
		e.setWallet(wallet);
		wallet.setTotalPending(wallet.getTotalPending().subtract(e.getMoney()));
		walletRepository.save(wallet);
		expenseService.addExpense(e);
		return expenseService.fromExpenseToDTO(e);
	}

}
