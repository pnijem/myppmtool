package com.pnijem.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pnijem.ppmtool.domain.ProjectTask;
import com.pnijem.ppmtool.repositories.BacklogRepository;
import com.pnijem.ppmtool.repositories.ProjectRepository;
import com.pnijem.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ProjectTaskRepository projectTaskRepository;

	public ProjectTask addProjectTask(){
		//Project Tasks to be added to a specific project, project not null
		return null;
	}
}
