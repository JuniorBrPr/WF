package app.rest;

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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody ObjectNode requestBody) {
        String email = requestBody.get("email").asText();
        String password = requestBody.get("password").asText();

        String usernameBeforeAt = email.split("@")[0]; // Extract the part before '@'

        if (password.equals(usernameBeforeAt)) {
            long randomId = new Random().nextLong();
            User user = new User(randomId, usernameBeforeAt, email, password, "registered user");

            // Generate JWT token using JWToken class
            String tokenString = jwtTokenGenerator
                    .encode(randomId, usernameBeforeAt, "registered user");

            return ResponseEntity.accepted()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString) // Attach token to the response header
                    .body(user);
        } else {
            throw new NotAcceptableStatusException("Login failed");
        }
    }
}
