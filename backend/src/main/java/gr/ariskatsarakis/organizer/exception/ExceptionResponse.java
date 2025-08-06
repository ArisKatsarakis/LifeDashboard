package gr.ariskatsarakis.organizer.exception;

import java.time.LocalDate;

public class ExceptionResponse {
        private LocalDate timestamp;
        private String errorMessage;
        private String location;

        public LocalDate getTimestamp() {
                return timestamp;
        }

        public void setTimestamp(LocalDate timestamp) {
                this.timestamp = timestamp;
        }

        public String getErrorMessage() {
                return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
                this.errorMessage = errorMessage;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

}
