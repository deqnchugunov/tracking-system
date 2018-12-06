package com.trackingsystem.controllers;

import com.trackingsystem.entities.Project;
import com.trackingsystem.services.ProjectsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectsServiceImpl projectsService;

    @Autowired
    public ProjectController(ProjectsServiceImpl projectsService){
        this.projectsService = projectsService;
    }

    @GetMapping("/projects")
    public String all(Model model) {
        List<Project> projects = projectsService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/all";
    }

    @GetMapping("/projects/{pattern}")
    public String projectByName(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        model.addAttribute("project", project);
        return "projects/details";
    }

    @GetMapping("/projects/{pattern}/issues")
    public String projectIssues(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        model.addAttribute("project", project);
        return "projects/issues";
    }

//    @GetMapping("/projects/{id}")
//    public String projectById(@PathVariable String id, Model model) {
//        Project project = projectsService.getProjectById(Integer.parseInt(id));
//        model.addAttribute("project", project);
//        return "projects/details";
//    }
}
