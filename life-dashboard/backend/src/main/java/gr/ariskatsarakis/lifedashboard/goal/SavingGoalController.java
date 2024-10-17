package gr.ariskatsarakis.lifedashboard.goal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SavingGoalController
 */
@RestController
@RequestMapping("/api/v1/sasving-goals")
public class SavingGoalController {

  @Autowired
  private SavingGoalService savingGoalService;

  @GetMapping
  public ResponseEntity<List<SavingGoal>> getSavingGoals() {
    return savingGoalService.findAllSavingGoals();
  }

  @PostMapping
  public ResponseEntity<SavingGoal> createSavingGoal(@RequestBody SavingGoal goal) {
    return savingGoalService.createSavingGoal(goal);
  }

}
