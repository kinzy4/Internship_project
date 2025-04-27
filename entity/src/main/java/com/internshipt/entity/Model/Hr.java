package com.internshipt.entity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.internshipt.users.Model.Person;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="hr")
public class Hr extends Person {

    @Column(name = "company_name", nullable = false)
    private String company_name;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "salary", nullable = false)
    private float salary;

    @OneToMany(mappedBy = "hr", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private Set<Internship> internships;

    // --- Getters and Setters ---

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Set<Internship> getInternships() {
        return internships;
    }

    public void setInternships(Set<Internship> internships) {
        this.internships = internships;
    }
}

