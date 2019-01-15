package com.pnijem.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message="Project Name is Required")
    private String projectName;

    @NotBlank(message="Project Identifier is Required")
    @Size(min = 4,max = 5, message = "Please use 4 to 5 Characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message="Project Description is Required")
    private String description;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date startDate;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date endDate;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updatedAt;

    public Project(){ }

    @PrePersist  //Executed before the entity manager persist operation is actually executed or cascaded. This call is synchronous with the persist operation.
    public void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate //Executed before the database UPDATE operation.
    public void onUpdate(){
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
