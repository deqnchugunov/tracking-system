package com.trackingsystem.controllers;

import com.trackingsystem.entities.Project;
import com.trackingsystem.entities.User;
import com.trackingsystem.services.ProjectsServiceImpl;
import com.trackingsystem.services.UsersServiceImpl;
import com.trackingsystem.services.base.ProjectsService;
import com.trackingsystem.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProjectController {

    private final ProjectsService projectsService;
    private final UsersService usersService;

    @Autowired
    public ProjectController(ProjectsService projectsService, UsersService usersService) {
        this.projectsService = projectsService;
        this.usersService = usersService;
    }

    @GetMapping("/projects")
    public String all(Model model, Principal principal) {
//      List<Project> projects = projectsService.getAllUsers();
        List<Project> projects = ((ProjectsServiceImpl) projectsService).getAllProjectsAssignedByUser(principal.getName());
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

    @GetMapping("/projects/{pattern}/documents")
    public String projectDocuments(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        model.addAttribute("project", project);
        return "projects/documents";
    }

    @GetMapping("/projects/{pattern}/settings")
    public String projectSettings(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        List<User> allUsers = usersService.getAllUsers();
        List<User> assignedUsers = ((UsersServiceImpl) usersService).getAllUsersAssignedToProject(project.getId());
        model.addAttribute("project", project);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("assignedUsers", assignedUsers);
        return "projects/settings";
    }

    private String createPatternFromProjectName(String projectName) {
        String pattern = projectName.replace(" ", "-").toLowerCase().trim();
        return pattern;
    }
}
