package com.internshipt.security.Repository;

import com.internshipt.security.Entity.RefreshToken;
import com.internshipt.users.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByPerson(Person person); // Delete refresh token by Person
}

