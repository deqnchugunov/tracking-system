package com.trackingsystem.web.controllers;

import com.trackingsystem.web.dto.ProjectDto;
import com.trackingsystem.persistance.entities.Project;
import com.trackingsystem.persistance.entities.User;
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
    public String newProject(Model model, Principal principal) {
        ProjectDto projectDto = new ProjectDto();
        model.addAttribute("project", projectDto);
        return "projects/new";
    }

    @PostMapping("/projects/new")
    public String newProject(@ModelAttribute ProjectDto projectDto) {
        projectsService.createProject(projectDto);
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

        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setPattern(project.getPattern());

        model.addAttribute("project", projectDto);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("assignedUsers", assignedUsers);
        return "projects/settings";
    }

    @GetMapping("/projects/{pattern}/users")
    public String projectGetAssignedUsers(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        List<User> assignedUsers = ((UsersServiceImpl) usersService).getAllUsersAssignedToProject(project.getId());
        model.addAttribute("project", project);
        model.addAttribute("assignedUsers", assignedUsers);
        return "projects/assignedUsers";
    }

    @GetMapping("/projects/{pattern}/users/update")
    public String projectUpdateAssignedUsers(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        List<User> allUsers = usersService.getAllUsers();
        List<User> assignedUsers = ((UsersServiceImpl) usersService).getAllUsersAssignedToProject(project.getId());
        model.addAttribute("project", project);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("assignedUsers", assignedUsers);
        return "projects/updateAssignedUsers";
    }

    @PostMapping("/projects/{pattern}/users/update")
    public String projectPostAssignedUsers(@PathVariable String pattern, Model model) {
        Project project = projectsService.getProjectByName(pattern);
        List<User> allUsers = usersService.getAllUsers();
        List<User> assignedUsers = ((UsersServiceImpl) usersService).getAllUsersAssignedToProject(project.getId());
        model.addAttribute("project", project);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("assignedUsers", assignedUsers);
        return "projects/updateAssignedUsers";
    }
}
