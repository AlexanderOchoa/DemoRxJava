package exchange.bck.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String user;
    private String password;
}