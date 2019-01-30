package com.trackingsystem.services.base;

import com.trackingsystem.persistance.entities.Role;
import java.util.List;

public interface RolesService {
    List<Role> getAllRoles();

    Role getRoleById(int id);

    Role getRoleByName(String name);

    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);
}
