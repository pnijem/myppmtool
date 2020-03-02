package com.pnijem.ppmtool.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity public class Project {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

	@NotBlank(message = "Project Name is Required") private String projectName;

	@NotBlank(message = "Project Identifier is Required") @Size(min = 4, max = 5, message = "Please use 4 to 5 Characters") @Column(updatable = false, unique = true) private String projectIdentifier;

	@NotBlank(message = "Project Description is Required") private String description;

	@JsonFormat(pattern = "yyyy-mm-dd") private Date startDate;

	@JsonFormat(pattern = "yyyy-mm-dd") private Date endDate;

	@JsonFormat(pattern = "yyyy-mm-dd") @Column(updatable = false) private Date createdAt;

	@JsonFormat(pattern = "yyyy-mm-dd") private Date updatedAt;
    //one Project has one backlog. One Backlog has one Project
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    //prevent unwanted recursion
	private Backlog backlog;

	public Project() {
	}

	@PrePersist  //Executed before the entity manager persist operation is actually executed or cascaded. This call is synchronous with the persist operation.
	public void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate //Executed before the database UPDATE operation.
	public void onUpdate() {
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

	public Backlog getBacklog() {
		return backlog;
	}

	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
}
