package com.santosh.repositories;

import com.santosh.models.Permissions;
import com.santosh.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long> {
    Collection<Object> getPermissionsByRole(Role savedRole);
}
