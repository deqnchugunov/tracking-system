package com.trackingsystem.services.base;

import com.trackingsystem.entities.Privilege;
import java.util.List;

public interface PrivilegesService {
    List<Privilege> getAllPrivileges();

    Privilege getPrivilegeById(int id);

    Privilege getPrivilegeByName(String name);

    void createPrivilege(Privilege privilege);

    void updatePrivilege(Privilege privilege);

    void deletePrivilege(Privilege privilege);
}
