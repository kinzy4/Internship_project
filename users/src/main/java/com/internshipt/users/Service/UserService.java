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
    public String login(String email, String password){
     if(userrepo.findByEmail(email).isPresent()){
      Person person =userrepo.findByEmail(email).get();
      if(password.equals(person.getPassword())){
          return "Login Successfull";
      }
      else{
          return "Password Incorrect";}
     }
     else{
         return "Email Doesn't Exist";}
    }

    }




