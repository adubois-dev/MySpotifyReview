package fr.spotify.review.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private UUID uuid;
  private String username;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, UUID uuid, String username, String email, List<String> roles) {
    this.token = accessToken;
    this.uuid = uuid;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

}
