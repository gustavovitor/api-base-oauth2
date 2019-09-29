package com.gustavovitor.apibase.repository.user;

import com.gustavovitor.apibase.domain.security.base.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUser(String email);
}
