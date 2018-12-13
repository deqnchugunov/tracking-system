package com.trackingsystem.controllers;

import com.trackingsystem.entities.Project;
import com.trackingsystem.services.BaseProjectService;
import com.trackingsystem.services.ProjectsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectController {

    private final BaseProjectService projectsService;

    @Autowired
    public ProjectController(BaseProjectService projectsService){
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

    @GetMapping("/projects/{pattern}/issues/new")
    public String projectNewIssue(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        model.addAttribute("project", project);
        return "projects/newIssue";
    }

    @GetMapping("/projects/new")
    public String newProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "projects/new";
    }

    @PostMapping("/projects/new")
    public String newProject(@ModelAttribute Project project) {
        String pattern = createPatternFromProjectName(project.getName());
        project.setPattern(pattern);
        projectsService.createProject(project);
        return "redirect:/projects";
    }

    private String createPatternFromProjectName(String projectName) {
        String pattern = projectName.replace(" ", "-").toLowerCase().trim();
        return pattern;
    }
}
