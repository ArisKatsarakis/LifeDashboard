package gr.ariskatsarakis.organizer.user.request;

public class RegisterResponse {
        private String message;
        private Long status;

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public Long getStatus() {
                return status;
        }

        public void setStatus(Long status) {
                this.status = status;
        }

}
