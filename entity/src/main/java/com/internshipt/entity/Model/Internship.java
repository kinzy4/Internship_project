package com.internshipt.entity.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.internshipt.entity.Model.Application;
import jakarta.persistence.*;
import com.internshipt.entity.Model.Application;

import java.util.Set;

@Entity
@Table(name = "Internship")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inter_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "requirments", nullable = false)
    private String requirments;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "period", nullable = false)
    private String period;

    @OneToMany(mappedBy = "internship")
    @JsonManagedReference
    @JsonIgnore
    private Set<Application> applications;
    @ManyToOne
    @JoinColumn(name = "hr_id", nullable = false)
    @JsonBackReference
    private Hr hr;

    public int getInter_id() {
        return inter_id;
    }

    public void setInter_id(int inter_id) {
        this.inter_id = inter_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequirments() {
        return requirments;
    }

    public void setRequirments(String requirments) {
        this.requirments = requirments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
    public Hr getHr() {
        return hr;
    }

    public void setHr(Hr hr) {
        this.hr = hr;
    }
}
