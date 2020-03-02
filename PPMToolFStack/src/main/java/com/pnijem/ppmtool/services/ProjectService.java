package com.pnijem.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pnijem.ppmtool.domain.Backlog;
import com.pnijem.ppmtool.domain.Project;
import com.pnijem.ppmtool.exceptions.ProjectIdException;
import com.pnijem.ppmtool.repositories.BacklogRepository;
import com.pnijem.ppmtool.repositories.ProjectRepository;

@Service //stereotype for service layer      
public class ProjectService {

	@Autowired private ProjectRepository projectRepository;

	@Autowired private BacklogRepository backlogRepository;

	public Project saveOrUpdateProject(Project project) {
		//TODO Logic
        final String identifier = project.getProjectIdentifier().toUpperCase();
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			if (project.getId() == null) { //create a new Backlog for project, only in case we are creating new Project
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(identifier);
			} else {
				project.setBacklog(backlogRepository.findByProjectIdentifier(identifier));
			}
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(String.format("Project ID '%s' already exists", identifier));
		}
	}

	public Project findProjectByIdentifier(String projectId) {

		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException(String.format("Project ID '%s' does not exist!", projectId));
		}

		return project;
	}

	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}

	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if (project == null) {
			throw new ProjectIdException(String.format("Project ID '%s' does not exist!", projectId));
		}

		projectRepository.delete(project);
	}

}
