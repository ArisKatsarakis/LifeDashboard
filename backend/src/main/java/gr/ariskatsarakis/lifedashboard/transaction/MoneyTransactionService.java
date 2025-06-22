package gr.ariskatsarakis.lifedashboard.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MoneyTransactionService {

	public List<MoneyTransaction> getTransacstion() {
		return new ArrayList<MoneyTransaction>();
	}
}
