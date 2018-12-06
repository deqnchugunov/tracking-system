package com.trackingsystem.services;

import com.trackingsystem.entities.Project;

import java.util.List;

public interface BaseProjectService {
    List<Project> getAllProjects();

    Project getProjectById(int id);

    Project getProjectByName(String name);

    void createProject(Project receipt);

    void updateProject(Project receipt);

    void deleteProject(Project receipt);
}
