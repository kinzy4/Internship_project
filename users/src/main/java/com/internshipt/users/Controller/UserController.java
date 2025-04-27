package com.internshipt.users.Controller;
import com.internshipt.users.Model.Person;
import com.internshipt.users.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Map;

import static com.internshipt.users.Model.Person.Type.hr;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Person person) {
        this.userservice.register(person);
        return ResponseEntity.ok("Registered Successfully");
    }

    @GetMapping("/login")
    public  ResponseEntity<String> login(@RequestBody Map<String,String> requestbody){
    String email=requestbody.get("email");
    String password=requestbody.get("password");
   String result=userservice.login(email,password);
        if (result.equals("Login Successfull")) {
            return ResponseEntity.ok(result);
        } else if (result.equals("Password Incorrect")) {
            return ResponseEntity.status(401).body(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }



}
