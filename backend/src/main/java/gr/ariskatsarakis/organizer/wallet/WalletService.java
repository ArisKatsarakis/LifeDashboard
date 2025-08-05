package gr.ariskatsarakis.organizer.wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import gr.ariskatsarakis.organizer.expenses.Expense;
import gr.ariskatsarakis.organizer.expenses.ExpenseDTO;
import gr.ariskatsarakis.organizer.expenses.ExpenseService;
import gr.ariskatsarakis.organizer.incomes.Income;
import gr.ariskatsarakis.organizer.incomes.IncomeDTO;
import gr.ariskatsarakis.organizer.incomes.IncomeService;
import gr.ariskatsarakis.organizer.user.UserInfo;
import gr.ariskatsarakis.organizer.user.UserInfoService;

/**
 * WalletService
 */
@Service
public class WalletService {

        private WalletRepository walletRepository;
        private IncomeService incomeService;
        private ExpenseService expenseService;
        private UserInfoService userInfoService;

        public WalletService(
                        WalletRepository walletRepository,
                        ExpenseService expenseService,
                        IncomeService incomeService,
                        UserInfoService userInfoService) {
                this.walletRepository = walletRepository;
                this.expenseService = expenseService;
                this.incomeService = incomeService;
                this.userInfoService = userInfoService;
        }

        private UserInfo fetchCurrentUserInfo() {

                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                                .getPrincipal();
                UserInfo userInfo = userInfoService.fetchUser(userDetails.getUsername());
                return userInfo;
        }

        public WalletDTO addWallet(WalletDTO wallet) {
                Wallet w = fromDTOToWallet(wallet);
                if (w.getTotalPending() == null) {
                        w.setTotalPending(BigDecimal.ZERO);
                }
                w.setUserInfo(fetchCurrentUserInfo());
                walletRepository.save(w);
                return fromWalletToDTO(w);

        }

        public List<WalletDTO> fetchWallets() {
                List<Wallet> wallets = walletRepository.findByUserInfo(fetchCurrentUserInfo());
                // List<Wallet> wallets = walletRepository.findAll();
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
                dto.setIncomes(w.getIncomes());
                dto.setExpenses(w.getExpenses());
                dto.setTotalExpenses(w.getTotalExpenses());
                dto.setTotalIncomes(w.getTotalIncomes());
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
                wallet.setTotalExpenses(
                                wallet.getTotalExpenses() != null
                                                ? wallet.getTotalExpenses().add(e.getMoney())
                                                : e.getMoney());
                expenseService.addExpense(e);

                List<Expense> walletExpenses = wallet.getExpenses() != null ? wallet.getExpenses() : new ArrayList<>();
                walletExpenses.add(e);
                wallet.setExpenses(walletExpenses);
                walletRepository.save(wallet);
                return expenseService.fromExpenseToDTO(e);
        }

        public IncomeDTO addWalletIncome(Wallet wallet, IncomeDTO dto) {
                Income i = incomeService.fromDTOToIncome(dto);
                i.setWallet(wallet);
                i = incomeService.addIncome(i);
                wallet.setTotalPending(wallet.getTotalPending().add(i.getMoney()));
                wallet.setTotalIncomes(
                                wallet.getTotalIncomes() != null
                                                ? wallet.getTotalIncomes().add(i.getMoney())
                                                : i.getMoney());

                List<Income> walletIncomes = wallet.getIncomes() != null ? wallet.getIncomes() : new ArrayList<>();
                walletIncomes.add(i);
                wallet.setIncomes(walletIncomes);
                walletRepository.save(wallet);
                return incomeService.fromIncomeToDTO(i);
        }

        public List<IncomeDTO> fetchWalletIncomes(Wallet wallet) {
                List<Income> incomes = wallet.getIncomes();
                List<IncomeDTO> dtos = new ArrayList<>();
                for (Income income : incomes) {
                        dtos.add(incomeService.fromIncomeToDTO(income));
                }
                return dtos;
        }

        public SingleWalletDTO fetchWalletByWalletId(Long walletId) {
                SingleWalletDTO dto = new SingleWalletDTO();
                Optional<Wallet> optionalWallet = walletRepository.findById(walletId);
                if (optionalWallet.isPresent()) {
                        dto.setTotalExpenses(optionalWallet.get().getTotalExpenses());
                        dto.setTotalIncomes(optionalWallet.get().getTotalIncomes());
                        dto.setWalletId(optionalWallet.get().getWalletId());
                        dto.setTotalPending(optionalWallet.get().getTotalPending());
                        dto.setWalletName(optionalWallet.get().getWalletName());
                        return dto;
                }
                return null;
        }

}
