package com.trackingsystem.services;

import com.trackingsystem.entities.Privilege;
import com.trackingsystem.repositories.base.GenericRepository;
import com.trackingsystem.services.base.PrivilegesService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class PrivilegesServiceImpl implements PrivilegesService {

    private final GenericRepository<Privilege> privilegeRepository;

    @Autowired
    public PrivilegesServiceImpl(GenericRepository<Privilege> privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }
    @Override
    public List<Privilege> getAllPrivileges() {
        return privilegeRepository.getAll();
    }

    @Override
    public Privilege getPrivilegeById(int id) {
        return privilegeRepository.getById(id);
    }

    @Override
    public Privilege getPrivilegeByName(String name) {
        Privilege privilege = privilegeRepository.getAll()
                .stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        return privilege;
    }

    @Override
    public void createPrivilege(Privilege role) {
        privilegeRepository.create(role);
    }

    @Override
    public void updatePrivilege(Privilege role) {
        privilegeRepository.update(role);
    }

    @Override
    public void deletePrivilege(Privilege role) {
        privilegeRepository.delete(role);
    }
}
