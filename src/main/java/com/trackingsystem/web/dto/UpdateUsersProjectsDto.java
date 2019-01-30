package com.trackingsystem.web.dto;

import com.trackingsystem.persistance.entities.Project;
import com.trackingsystem.persistance.entities.User;
import java.util.List;

public class UpdateUsersProjectsDto {

    private Project project;

    private List<User> users;
}
