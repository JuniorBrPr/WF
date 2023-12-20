package app.rest;

import app.APIConfig;
import app.JWToken;
import app.models.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.Random;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private JWToken jwtTokenGenerator; // Autowiring JWToken

    @Autowired
    APIConfig apiConfig;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody ObjectNode requestBody) {
        String email = requestBody.get("email").asText();
        String password = requestBody.get("password").asText();

        String usernameBeforeAt = email.split("@")[0]; // Extract the part before '@'

        if (password.equals(usernameBeforeAt)) {
            long randomId = new Random().nextLong();
            User user = new User(randomId, usernameBeforeAt, email, password, "registered user");

            // Set values for JWToken
            jwtTokenGenerator.setCallname(usernameBeforeAt);
            jwtTokenGenerator.setAccountId(randomId);
            jwtTokenGenerator.setRole("registered user");

            // Generate JWT token using JWToken class
            String tokenString = jwtTokenGenerator.encode(this.apiConfig.getPassphrase(), this.apiConfig.getIssuer(), this.apiConfig.getExpiration()); // Change expiration as needed

            return ResponseEntity.accepted()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString) // Attach token to the response header
                    .body(user);
        } else {
            throw new NotAcceptableStatusException("Login failed");
        }
    }

}
