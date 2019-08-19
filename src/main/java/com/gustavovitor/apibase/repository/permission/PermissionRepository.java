package com.gustavovitor.apibase.repository.permission;

import com.gustavovitor.apibase.domain.security.base.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {}
