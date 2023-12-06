package app;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
public class APIConfig {
    private List<String> SECURED_PATHS;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.passphrase}")
    private String passphrase;

    @Value("${jwt.expiration}")
    private int expiration;
}
