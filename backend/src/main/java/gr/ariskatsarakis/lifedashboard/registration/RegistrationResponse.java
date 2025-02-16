package gr.ariskatsarakis.lifedashboard.registration;

import lombok.ToString;

@ToString
public class RegistrationResponse {
  private String messasge;

  public RegistrationResponse(String messasge) {
    this.messasge = messasge;
  }

  public RegistrationResponse() {

  }

  public String getMessasge() {
    return messasge;
  }

  public void setMessasge(String messasge) {
    this.messasge = messasge;
  }

}
