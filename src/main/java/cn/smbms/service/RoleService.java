package cn.smbms.service;

import cn.smbms.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectRoles();

    Role selectById(Long id);
}
