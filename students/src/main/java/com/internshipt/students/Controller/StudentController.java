package com.internshipt.students.Controller;

import com.internshipt.students.Service.StudentService;
import com.internshipt.users.Model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.internshipt.entity.Model.*;
import com.internshipt.users.Model.Person.Type;
@RestController
 @RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  Student st){
        st.setType(Type.student);
        studentService.register_as_student(st);
        return ResponseEntity.ok("Registered Successfully");
    }
    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<String> update_profile(@PathVariable int id,@RequestBody Student student){
        String result=studentService.update_profile(student,id);
        if (result.equals("Profile updated successfully!")) {
            return ResponseEntity.ok(result);}
        else {
            return ResponseEntity.status(404).body(result);
        }
    }
}
