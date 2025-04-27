package com.internshipt.internships.Service;

import com.internshipt.entity.Model.Internship;
import com.internshipt.internships.Repository.InternRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InternshipService {
    private final InternRepo internRepo;

    public InternshipService(InternRepo internRepo) {
        this.internRepo = internRepo;
    }
    public String add_inter(Internship inter) {
        try {
            internRepo.save(inter);
            return "Internship added successfully!";
        } catch (Exception e) {
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

}
