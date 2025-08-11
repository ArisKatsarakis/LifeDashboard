package gr.ariskatsarakis.organizer.exception;

public class SavingGoalNotFoundException extends RuntimeException {

        private Long savingGoalId;

        public SavingGoalNotFoundException(Long savingGoalId) {
                super("Saving goal not found wwith id: " + savingGoalId);
                this.savingGoalId = savingGoalId;
        }

        public Long getSavingGoalId() {
                return savingGoalId;
        }

}
