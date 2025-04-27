package com.internshipt.internships.Repository;


import com.internshipt.entity.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternRepo extends JpaRepository<Internship,Integer> {

}
