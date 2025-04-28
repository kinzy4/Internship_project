package com.internshipt.users.Controller;
import com.internshipt.users.Model.Person;
import com.internshipt.users.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Map;

import static com.internshipt.users.Model.Person.Type.hr;
@CrossOrigin(origins = "http://localhost:3000")
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");
        String result = userservice.login(email, password);

        if ("Login Successful".equals(result)) { // safer string comparison
            return ResponseEntity.ok("Login Successful"); // send 200 OK
        } else if ("Password Incorrect".equals(result)) {
            return ResponseEntity.status(401).body("Password Incorrect"); // send 401 Unauthorized
        } else if ("User Not Found".equals(result)) {
            return ResponseEntity.status(404).body("User Not Found"); // send 404 Not Found
        } else {
            return ResponseEntity.status(500).body("Unknown Error"); // unexpected case
        }
    }






}
