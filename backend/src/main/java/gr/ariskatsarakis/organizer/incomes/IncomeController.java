package gr.ariskatsarakis.organizer.incomes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {

        private IncomeService incomeService;

        public IncomeController(IncomeService incomeService) {
                this.incomeService = incomeService;
        }

        @GetMapping("/categories")
        public ResponseEntity<IncomeCategory[]> fetchIncomeCategories() throws Exception {
                throw new Exception("Testing the exception");
                // return new ResponseEntity<>(IncomeCategory.values(), HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity<List<IncomeDTO>> fetchIncomes() {
                return new ResponseEntity<>(incomeService.fetchUserIncomes(), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<IncomeDTO> addIncome(@RequestBody IncomeDTO incomeDTO) {
                return new ResponseEntity<>(incomeService.addIncome(incomeDTO), HttpStatus.OK);
        }
}
