package gr.ariskatsarakis.lifedashboard.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * SavingGoalService
 */
@Service
public class SavingGoalService {
  @Autowired
  SavingGoalRepository savingGoalRepository;

  public ResponseEntity<SavingGoal> createSavingGoal(SavingGoal savingGoal) {
    return new ResponseEntity(savingGoalRepository.save(savingGoal), HttpStatus.OK);
  }
}
