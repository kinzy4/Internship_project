package com.internshipt.security.Service;

import com.internshipt.security.Entity.RefreshToken;
import com.internshipt.security.Repository.RefreshTokenRepo;
import com.internshipt.users.Model.Person;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepo refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepo refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // Create a refresh token using Person (instead of User)
    public String createRefreshToken(Person person) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(person); // Update to set Person instead of User
        refreshToken.setExpiryDate(Instant.now().plusSeconds(7 * 24 * 60 * 60)); // 7 days
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }

    // Validate a refresh token
    public boolean validateRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .filter(rt -> rt.getExpiryDate().isAfter(Instant.now()))
                .isPresent();
    }

    // Delete the refresh token associated with a Person
    public void deleteRefreshToken(Person person) {
        refreshTokenRepository.deleteByPerson(person); // Use deleteByPerson instead of deleteByUser
    }
}
