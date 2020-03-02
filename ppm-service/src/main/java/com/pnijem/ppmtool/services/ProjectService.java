package com.pnijem.ppmtool.services;

import com.pnijem.ppmtool.domain.Project;
import com.pnijem.ppmtool.exceptions.ProjectIdException;
import com.pnijem.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //stereotype for service layer      
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project){
        //TODO Logic
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e){
            throw new ProjectIdException(String.format("Project ID '%s' already exists",
                    project.getProjectIdentifier().toUpperCase()));
        }
    }

    public Project findProjectByIndentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException(String.format("Project ID '%s' does not exist!", projectId));
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project == null){
            throw new ProjectIdException(String.format("Project ID '%s' does not exist!", projectId));
        }

        projectRepository.delete(project);
    }

}
