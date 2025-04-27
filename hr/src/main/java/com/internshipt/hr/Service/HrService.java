package com.internshipt.hr.Service;

import com.internshipt.entity.Model.Hr;
import com.internshipt.entity.Model.Student;
import com.internshipt.hr.Repository.HrRepo;
import com.internshipt.users.Model.Person;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HrService {
    private final HrRepo hrrepo;

    public HrService(HrRepo hrrepo) {
        this.hrrepo = hrrepo;
    }
    public void addhr(Hr hr){
        hrrepo.save(hr);
    }
    public String update_profile(Hr hr, int id) {
        Optional<Hr> h_r= hrrepo.findById(id);
        if (h_r.isPresent()) {
            Hr hrr = h_r.get();
            hrr.setName(hr.getName());
            hrr.setEmail(hr.getEmail());
            hrr.setPassword(hr.getPassword());
            hrr.setAddress(hr.getAddress());
            hrr.setAge(hr.getAge());
            hrr.setCompany_name(hr.getCompany_name());
            hrr.setPosition(hr.getPosition());
            hrr.setSalary(hr.getSalary());
            hrr.setType(Person.Type.hr);

            hrrepo.save(hrr);
            return "Profile updated successfully!";
        } else {
            return "Person not found!";
        }}
}
