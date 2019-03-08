package com.trackingsystem.web.dto;

import com.trackingsystem.persistance.entities.Project;
import com.trackingsystem.persistance.entities.User;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class UpdateUsersProjectsDto {

    @NotNull
    private Project project;

    @NotNull
    private Map<User, Integer> users = new TreeMap<>();

    //

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Map<User, Integer> getUsers() {
        return users;
    }

    public void setUsers(Map<User, Integer> users) {
        this.users = users;
    }
}
