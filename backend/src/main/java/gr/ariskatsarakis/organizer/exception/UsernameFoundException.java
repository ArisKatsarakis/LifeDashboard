package gr.ariskatsarakis.organizer.exception;

public class UsernameFoundException extends RuntimeException {

        private String username;

        public UsernameFoundException(String username) {
                super(String.format("Username with email: %s is already register", username));
                this.username = username;

        }

}
