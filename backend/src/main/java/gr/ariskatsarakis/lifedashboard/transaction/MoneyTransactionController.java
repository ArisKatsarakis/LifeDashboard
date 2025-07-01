package gr.ariskatsarakis.lifedashboard.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import gr.ariskatsarakis.lifedashboard.jwt.JwtHelper;
import gr.ariskatsarakis.lifedashboard.user.AppUser;
import gr.ariskatsarakis.lifedashboard.user.AppUserRepository;

@RestController
public class MoneyTransactionController {

	private Logger _log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MoneyTransactionService mTService;

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	private JwtHelper jwtHelper;

	@GetMapping("api/v1/transactions")
	public ResponseEntity<List<MoneyTransactionDTO>> getTransactions(
			@RequestHeader(name = "Authorization") String token) {
		MoneyTransaction mt = new MoneyTransaction();

		token = token.substring(7);
		_log.info(String.format("Token received %s", token));
		String username = this.jwtHelper.getUsernameFromToken(token);
		Optional<AppUser> appuser = appUserRepository.findByUsername(username);
		if (appuser.isPresent()) {
			mt.setAppUser(appuser.get());
		}

		mt.setMoney(BigDecimal.TEN);
		List<MoneyTransactionDTO> dtos = new ArrayList<>();
		dtos.add(new MoneyTransactionDTO(mt));
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
