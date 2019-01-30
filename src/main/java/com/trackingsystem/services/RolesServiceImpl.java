package com.trackingsystem.services;

import com.trackingsystem.persistance.entities.Role;
import com.trackingsystem.repositories.base.GenericRepository;
import com.trackingsystem.services.base.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class RolesServiceImpl implements RolesService {

    private final GenericRepository<Role> roleRepository;

    @Autowired
    public RolesServiceImpl(GenericRepository<Role> roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAll();
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        Role role = roleRepository.getAll()
                .stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        return role;
    }

    @Override
    public void createRole(Role role) {
        roleRepository.create(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.update(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}
