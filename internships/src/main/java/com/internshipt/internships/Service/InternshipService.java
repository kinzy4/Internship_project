package com.internshipt.internships.Service;

import com.internshipt.entity.Model.Application;
import com.internshipt.entity.Model.Hr;
import com.internshipt.entity.Model.Internship;
import com.internshipt.internships.Repository.InternRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InternshipService {
    private final InternRepo internRepo;

    public InternshipService(InternRepo internRepo) {
        this.internRepo = internRepo;
    }

    @Transactional
    public String add_inter(Internship inter, Hr hr) {
        try {
            inter.setHr(hr);
            internRepo.save(inter);
            internRepo.flush(); // force Hibernate to send to DB immediately
            return "Internship added Successfully";
        } catch (Exception e) {
            e.printStackTrace();  // <-- important: shows real cause in console
            return "Error adding internship: " + e.getMessage();
        }
    }


    public String update_inter(Internship inter, int id) {
        Optional<Internship> intr = internRepo.findById(id);
        if (intr.isPresent()) {
            Internship existingInternship = intr.get();

            existingInternship.setTitle(inter.getTitle());
            existingInternship.setRequirments(inter.getRequirments());
            existingInternship.setType(inter.getType());
            existingInternship.setLocation(inter.getLocation());
            existingInternship.setPeriod(inter.getPeriod());
            existingInternship.setHr(inter.getHr());
            internRepo.save(existingInternship);

            return "Internship updated successfully!";
        } else {
            return "Internship with ID " + id + " not found!";
        }
    }
    public List<Internship> getAllInternships() {
        return internRepo.findAll();
    }

}