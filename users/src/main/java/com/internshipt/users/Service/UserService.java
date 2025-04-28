package com.internshipt.users.Service;

import com.internshipt.users.Repository.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import  com.internshipt.users.Model.Person;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userrepo;


    public UserService(UserRepo userrepo) {
        this.userrepo = userrepo;
    }
    public void register(Person person){
        userrepo.save(person);
    }
    public String login(String email, String password) {
        Optional<Person> optionalPerson = userrepo.findByEmail(email);

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            if (password.equals(person.getPassword())) {
                return "Login Successful"; // ✅ Correct spelling
            } else {
                return "Password Incorrect"; // ✅ Matches controller
            }
        } else {
            return "User Not Found"; // ✅ Matches controller
        }
    }


}




