package com.internshipt.entity.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.internshipt.entity.Model.*;
import com.internshipt.users.Model.Person;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Student")
public class Student extends Person {

    public Student() {

    }
    @Column(name = "university", nullable = false)
    private String university;

    @Column(name = "college", nullable = false)
    private String college;

    @Column(name = "major", nullable = false)
    private String major;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "gpa", nullable = false)
    private float gpa;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    @JsonIgnore
    private Set<Application> applications;

    // Getters and Setters

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}

