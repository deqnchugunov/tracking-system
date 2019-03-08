package com.trackingsystem.services;

import com.trackingsystem.persistance.entities.User;
import com.trackingsystem.repositories.ProjectRepository;
import com.trackingsystem.repositories.UserRepository;
import com.trackingsystem.web.dto.ProjectDto;
import com.trackingsystem.persistance.entities.Project;
import com.trackingsystem.repositories.base.GenericRepository;
import com.trackingsystem.services.base.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectsServiceImpl implements ProjectsService {

    private final GenericRepository<Project> projectRepository;
    private final ProjectRepository projectRepo;
    private final GenericRepository<User> usersRepository;
    private final UserRepository userRepo;

    @Autowired
    public ProjectsServiceImpl(GenericRepository<Project> projectRepository,
                               GenericRepository<User> usersRepository,
                               ProjectRepository projectRepo,
                               UserRepository userRepo) {
        this.projectRepository = projectRepository;
        this.usersRepository = usersRepository;
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.getAll();
    }

    @Override
    public Project getProjectById(int id) {
        return projectRepository.getById(id);
    }

    @Override
    public Project getProjectByName(String pattern) {
        Project project = projectRepository.getAll()
                    .stream()
                    .filter(p -> p.getPattern().equals(pattern))
                    .findFirst()
                    .orElse(null);

//        ProjectDto projectDto = new ProjectDto();
//        projectDto.setName(project.getName());
//        projectDto.setDescription(project.getDescription());
        return project;
    }

    @Override
    public void createProject(ProjectDto projectDto) {
        Project project = new Project();

        project.setName(projectDto.getName());
        String pattern = createPatternFromProjectName(projectDto.getName());
        project.setPattern(pattern);
        project.setDescription(projectDto.getDescription());
        Project createdProject = projectRepository.create(project);

        assignProjectToAdminUsers(createdProject.getId());
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.update(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public List<Project> getProjectsAssignedToUser(String username) {
        return projectRepo.getProjectsAssignedToUser(username);
    }

    public boolean hasUserAccessToProject(String pattern, String username) {
        Project project = getProjectByName(pattern);
        List<Project> assignedProjects = getProjectsAssignedToUser(username);

        for (Project p : assignedProjects) {
            if(p.getId() == project.getId()) return true;
        }
        return false;
    }

    // Helpers

    private String createPatternFromProjectName(String projectName) {
        String pattern = projectName.replace("- ", "-").replace(" -", "-").replace(" ", "-").toLowerCase().trim();
        return pattern;
    }

    private void assignProjectToAdminUsers(int projectId) {
        userRepo.assignProjectToAdminUsers(projectId);
    }

    private void getUsersAssignedToProject1(String projectName) {
        Map<User, Integer> users = new HashMap<>();

        List<User> allUsers = usersRepository.getAll();
//        List<User> assignedUsers = getUsersAssignedToProject(projectName);

//        for(User user : allUsers) {
//            for(User assignedUser : assignedUsers) {
//                if (user.getId() != assignedUser.getId()) {
//                    updateUsersProjectsDto.getUsers().put(user, 0);
//                } else {
//                    updateUsersProjectsDto.getUsers().put(user, 1);
//                    break;
//                }
//            }
//        }
    }


    //    public UpdateUsersProjectsDto getProjectAndAssignedUsers(String pattern) {
//        Project project = getProjectByName(pattern);
//
//        UpdateUsersProjectsDto updateUsersProjectsDto = new UpdateUsersProjectsDto();
//        updateUsersProjectsDto.setProject(project);
//
//        dateUsersProjectsDto;
//    }

}
