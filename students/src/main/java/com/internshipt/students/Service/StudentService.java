package com.internshipt.students.Service;

import com.internshipt.students.Repository.StudentRepo;
import com.internshipt.users.Model.Person;
import org.springframework.stereotype.Service;
import com.internshipt.entity.Model.*;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;}

    public void register_as_student(Student st){
        studentRepo.save(st);

    }
    public String update_profile(Student student, int id) {
        Optional<Student> st= studentRepo.findById(id);
        if (st.isPresent()) {
            Student est = st.get();
            est.setName(student.getName());
            est.setEmail(student.getEmail());
            est.setPassword(student.getPassword());
            est.setAddress(student.getAddress());
            est.setAge(student.getAge());
            est.setType(Person.Type.student);
            est.setUniversity(student.getUniversity());
            est.setCollege(student.getCollege());
            est.setMajor(student.getMajor());
            est.setLevel(student.getLevel());
            est.setGpa(student.getGpa());

            studentRepo.save(est);
            return "Profile updated successfully!";
        } else {
            return "Person not found!";
        }}
}
