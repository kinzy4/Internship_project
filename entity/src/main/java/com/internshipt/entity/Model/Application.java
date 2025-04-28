package com.internshipt.entity.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Application")
public class Application {

    public enum Status {
        Pending,
        Reviewing,
        Accepted,
        Rejected
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "st_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "inter_id")
    @JsonBackReference
    private Internship internship;

    // Change cvFile from byte[] to String to store the URL
    @Column(name = "cv_file_url")
    private String cvFileUrl;

    private String fileType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public String getCvFileUrl() {
        return cvFileUrl;
    }

    public void setCvFileUrl(String cvFileUrl) {
        this.cvFileUrl = cvFileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

