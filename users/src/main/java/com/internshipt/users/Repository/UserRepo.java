package com.internshipt.users.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.internshipt.users.Model.Person;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Person,Integer> {
  public Optional<Person> findByEmail(String email);
}
