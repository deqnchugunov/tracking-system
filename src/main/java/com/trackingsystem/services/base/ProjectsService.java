package com.trackingsystem.services.base;

import com.trackingsystem.web.dto.ProjectDto;
import com.trackingsystem.persistance.entities.Project;

import java.util.List;

public interface ProjectsService {
    List<Project> getAllProjects();

    Project getProjectById(int id);

    Project getProjectByName(String name);

    void createProject(ProjectDto receipt);

    void updateProject(Project receipt);

    void deleteProject(Project receipt);
}
