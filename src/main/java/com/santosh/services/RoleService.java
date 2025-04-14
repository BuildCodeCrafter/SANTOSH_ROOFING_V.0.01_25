package com.santosh.services;

import com.santosh.models.ApiResponse;
import com.santosh.models.Permissions;
import com.santosh.models.Privilege;
import com.santosh.models.Role;
import com.santosh.models.custom_exception.ResourceNotFoundException;
import com.santosh.models.dtos.RoleDto;

import java.util.List;

public interface RoleService {
    Role createRole(RoleDto roleDto);
    List<RoleDto> getAllRoles();
    List<Permissions> createPermissions(List<Permissions> permissions);
    Privilege createPrivilege(Privilege privilege);

    RoleDto getRoleByRoleName(String roleName) throws ResourceNotFoundException;

    Role updateRole(RoleDto roleDto) throws ResourceNotFoundException;

    ApiResponse<?> deleteRole(Long roleId);

    Role findById(Long roleId);
}
