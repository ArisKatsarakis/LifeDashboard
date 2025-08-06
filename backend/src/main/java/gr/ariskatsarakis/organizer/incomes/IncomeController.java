package gr.ariskatsarakis.organizer.incomes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {

        @GetMapping("/categories")
        public ResponseEntity<IncomeCategory[]> fetchIncomeCategories() {
                return new ResponseEntity<>(IncomeCategory.values(), HttpStatus.OK);
        }
}
