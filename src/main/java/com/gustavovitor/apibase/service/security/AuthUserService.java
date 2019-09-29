package com.gustavovitor.apibase.service.security;

import com.gustavovitor.apibase.domain.security.base.AuthUser;
import com.gustavovitor.apibase.domain.security.base.Permission;
import com.gustavovitor.apibase.repository.permission.PermissionRepository;
import com.gustavovitor.apibase.repository.user.AuthUserRepository;
import com.gustavovitor.apibase.service.maker.ServiceMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthUserService extends ServiceMaker<AuthUserRepository, AuthUser> {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public AuthUser insert(AuthUser user) {
        user.setId(null);
        user.setPass(encoder.encode(user.getPass()));
        setDefaultUserPermissions(user);
        return super.insert(user);
    }

    private void setDefaultUserPermissions(AuthUser user) {
        List<Permission> permissions = permissionRepository.findAll();

        List<Permission> userDefaultPermissions = new ArrayList<>();
        userDefaultPermissions
                .add(permissions
                        .stream()
                        .filter(permission -> permission
                                .getDescription()
                                .equals("READ_USERS"))
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Permissão não encontrada!"))
                );

        user.setPermissions(userDefaultPermissions);
    }

}
