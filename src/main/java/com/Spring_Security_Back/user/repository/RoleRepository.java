package com.Spring_Security_Back.user.repository;

import com.Spring_Security_Back.user.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRol(String rol);
}
