package com.internshipt.hr.Controller;

import com.internshipt.entity.Model.Hr;
import com.internshipt.entity.Model.Internship;
import com.internshipt.entity.Model.Student;
import com.internshipt.hr.Service.HrService;
import com.internshipt.internships.Service.InternshipService;
import com.internshipt.users.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.internshipt.users.Model.Person.Type.hr;

@RestController
@RequestMapping("/hr")
public class HrController {
    private final HrService hrService;
    private final InternshipService internshipService;

    public HrController(HrService hrService, InternshipService internshipService) {
        this.hrService = hrService;


        this.internshipService = internshipService;
    }
    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<String> update_profile(@PathVariable int id, @RequestBody Hr hr){
        String result=hrService.update_profile(hr,id);
        if (result.equals("Profile updated successfully!")) {
            return ResponseEntity.ok(result);}
        else {
            return ResponseEntity.status(404).body(result);
        }
    }
    @RequestMapping(value = "/addhr", method = RequestMethod.POST)
     public ResponseEntity<String> addhr(@RequestBody Hr hrr){
        hrr.setType(hr);
        hrService.addhr(hrr);
        return ResponseEntity.ok("Hr added Successfully");
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Controller is working!");
    }
    @PostMapping("/addinternship")
    public ResponseEntity<String> addinternship(@RequestBody Internship inter){
        
        internshipService.add_inter(inter);
        return ResponseEntity.ok("Internship added Successfully");
    }

    @PutMapping("/updateinternship/{id}")
    public ResponseEntity<String> update_internship(@PathVariable int id, @RequestBody Internship inter){
        String result=internshipService.update_inter(inter,id);
        if (result.equals("Internship updated successfully!")) {
            return ResponseEntity.ok(result);}
        else {
            return ResponseEntity.status(404).body(result);
        }
    }
}
