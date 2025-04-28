package com.internshipt.students.Controller;

import com.internshipt.applications.Service.AppService;
import com.internshipt.internships.Service.InternshipService;
import com.internshipt.students.Service.StudentService;
import com.internshipt.users.Model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.internshipt.entity.Model.*;
import com.internshipt.users.Model.Person.Type;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.internshipt.entity.Model.Application.Status.Pending;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
 @RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final AppService appService;
    private final InternshipService internshipService;
    public StudentController(StudentService studentService, AppService appService, InternshipService internshipService) {
        this.studentService = studentService;
        this.appService = appService;
        this.internshipService = internshipService;
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

    @PostMapping("/submitapplication")
    public ResponseEntity<String> submit_app(@RequestBody Application app)  {
    app.setStatus(Pending);
        appService.submit_app(app);
        return ResponseEntity.ok("Application  submitted successfully!");
    }
    @GetMapping("/student/internships")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Internship> getAllInternships() {
        return internshipService.getAllInternships();
    }

}
