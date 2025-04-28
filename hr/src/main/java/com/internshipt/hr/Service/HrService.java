package com.internshipt.hr.Service;

import com.internshipt.entity.Model.Hr;
import com.internshipt.entity.Model.Student;
import com.internshipt.hr.Repository.HrRepo;
import com.internshipt.users.Model.Person;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HrService {
    private final HrRepo hrrepo;
    private JavaMailSender mailSender;
    public HrService(HrRepo hrrepo, JavaMailSender mailSender) {
        this.hrrepo = hrrepo;
        this.mailSender = mailSender;
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

    public void sendAcceptanceEmail(String toEmail, String internshipTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mkinzy71@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Application Status: Accepted");
        message.setText("Congratulations! Your application for the internship '" + internshipTitle + "' has been accepted.");



        try {
            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (MailException e) {
            e.printStackTrace();

        }
    }
    public void sendRejectionEmail(String toEmail, String internshipTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mkinzy71@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Application Status: Rejected");
        message.setText("We regret to inform you that your application for the internship '" + internshipTitle + "' has been rejected. "
                + "Thank you for your interest and we encourage you to apply for future opportunities.");

        mailSender.send(message);
    }

}
