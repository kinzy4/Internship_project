package com.internshipt.applications.Repository;

import com.internshipt.entity.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends JpaRepository<Application,Integer> {

}
