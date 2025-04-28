
package com.internshipt.security.Controller;

import com.internshipt.security.Entity.AuthResponse;
import com.internshipt.security.Entity.TokenRefreshRequest;
import com.internshipt.security.Service.RefreshTokenService;
import com.internshipt.security.jwt.JwtService;
import com.internshipt.users.Model.Person; // Import Person model
import com.internshipt.users.Repository.UserRepo; // Ensure you're using the correct repository
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserRepo personRepository; // Use PersonRepository

    public AuthController(JwtService jwtService, RefreshTokenService refreshTokenService, AuthenticationManager authenticationManager, UserRepo personRepository) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository; // Correct repository reference
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        // Authenticate using email and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Fetch the person by email
        Person person = personRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        // Generate access token and refresh token using the email
        String accessToken = jwtService.generateAccessToken(person.getEmail());
        String refreshToken = refreshTokenService.createRefreshToken(person);

        // Return the response with access and refresh tokens
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody TokenRefreshRequest request) {
        // Validate the refresh token
        if (refreshTokenService.validateRefreshToken(request.getRefreshToken())) {
            // Extract the email from the refresh token (since you use email for login)
            String email = jwtService.extractEmail(request.getRefreshToken());

            // Fetch the person by email to ensure the user exists
            Person person = personRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate a new access token using the email
            String newAccessToken = jwtService.generateAccessToken(person.getEmail());

            // Return the response with the new access token and the same refresh token
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("accessToken", newAccessToken);
                put("refreshToken", request.getRefreshToken());
            }});
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
        }
    }


}
