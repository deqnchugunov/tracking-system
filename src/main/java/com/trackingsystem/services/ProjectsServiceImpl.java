package com.trackingsystem.services;

import com.trackingsystem.entities.Project;
import com.trackingsystem.repositories.base.GenericRepository;
import com.trackingsystem.services.base.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ProjectsServiceImpl implements ProjectsService {

    private final GenericRepository<Project> projectRepository;

    @Autowired
    public ProjectsServiceImpl(GenericRepository<Project> projectRepository) {
        this.projectRepository = projectRepository;
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

        return project;
    }

    @Override
    public void createProject(Project project) {
        projectRepository.create(project);
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.update(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
