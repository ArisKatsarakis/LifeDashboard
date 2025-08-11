package gr.ariskatsarakis.organizer.savinggoals;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/saving-goals")
public class SavingGoalController {

        private SavingGoalService savingGoalService;

        public SavingGoalController(SavingGoalService savingGoalService) {
                this.savingGoalService = savingGoalService;
        }

        @GetMapping
        public ResponseEntity<List<SavingGoal>> fetchSavingGoalsByUser() {
                return new ResponseEntity<>(savingGoalService.fetchSavingGoalsByUser(), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<SavingGoal> addSavingGoal(@RequestBody SavingGoal savingGoal) {
                return new ResponseEntity<>(savingGoalService.addSavingGoal(savingGoal), HttpStatus.OK);
        }

        @GetMapping("/{savingGoalId}")
        public ResponseEntity<SavingCalculations> fetchCalculationsForSavingGoals(@PathVariable Long savingGoalId) {
                return new ResponseEntity<>(savingGoalService.fetchCalcualtions(savingGoalId), HttpStatus.OK);

        }
}
