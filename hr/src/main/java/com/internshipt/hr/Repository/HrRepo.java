package com.internshipt.hr.Repository;
import com.internshipt.entity.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HrRepo extends JpaRepository<Hr,Integer>  {
}
