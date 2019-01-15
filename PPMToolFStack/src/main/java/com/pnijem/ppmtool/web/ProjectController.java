package com.pnijem.ppmtool.web;

import com.pnijem.ppmtool.domain.Project;
import com.pnijem.ppmtool.services.MapValidationErrorService;
import com.pnijem.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationErrorService validationService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = validationService.mapValidationService(result);
        if(errorMap!=null) {
            return errorMap;
        }
        projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById (@PathVariable String projectId){

        Project project = projectService.findProjectByIndentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>(String.format("Project with ID '%s' was deleted", projectId), HttpStatus.OK);
    }
}
