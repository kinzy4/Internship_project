package com.internshipt.hr.Controller;

import com.internshipt.applications.Service.AppService;
import com.internshipt.entity.Model.Application;
import com.internshipt.entity.Model.Hr;
import com.internshipt.entity.Model.Internship;
import com.internshipt.entity.Model.Student;
import com.internshipt.hr.Repository.HrRepo;
import com.internshipt.hr.Service.HrService;
import com.internshipt.internships.Service.*;
import com.internshipt.users.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.internshipt.users.Model.Person.Type.hr;

@RestController
@RequestMapping("/hr")
public class HrController {
    private final HrService hrService;
    private final InternshipService internshipService;
    private final HrRepo hrRepo;
    private final AppService appService;
    public HrController(HrService hrService, InternshipService internshipService, HrRepo hrRepo, AppService appService) {
        this.hrService = hrService;


        this.internshipService = internshipService;
        this.hrRepo = hrRepo;

        this.appService = appService;
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


        Hr hr = hrRepo.findById(inter.getHr().getUser_id())
                .orElseThrow(() -> new RuntimeException("HR not found"));

        internshipService.add_inter(inter, hr);
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
    @PutMapping("/updatestatus/{id}")
    public ResponseEntity<String> update_status(@PathVariable int id, @RequestBody Map<String, String> body) {
        try {
            String statusValue = body.get("status");
            Application.Status status = Application.Status.valueOf(statusValue);

           Application application=appService.update_status(id,status);

            if (application == null) {
                return ResponseEntity.status(404).body("Application not found with ID: " + id);
            }
            if (status == Application.Status.Accepted) {
                String studentEmail = application.getStudent().getEmail();
                String internshipTitle = application.getInternship().getTitle();

                hrService.sendAcceptanceEmail(studentEmail, internshipTitle);
            }


            else if (status == Application.Status.Rejected) {
                String studentEmail = application.getStudent().getEmail();
                String internshipTitle = application.getInternship().getTitle();

                hrService.sendRejectionEmail(studentEmail, internshipTitle);
            }

            return ResponseEntity.ok("Status updated successfully!");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status value provided.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error.");
        }
    }

}
