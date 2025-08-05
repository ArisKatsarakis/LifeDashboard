package gr.ariskatsarakis.organizer.user.request;

public class LoginResponse {

        private String jwtToken;
        private String expiration;
        private String username;

        public String getJwtToken() {
                return jwtToken;
        }

        public void setJwtToken(String jwtToken) {
                this.jwtToken = jwtToken;
        }

        public String getExpiration() {
                return expiration;
        }

        public void setExpiration(String expiration) {
                this.expiration = expiration;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

}
