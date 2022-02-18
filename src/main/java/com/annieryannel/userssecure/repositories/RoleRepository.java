package com.annieryannel.userssecure.repositories;

import com.annieryannel.userssecure.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Short> {
    Role findByRole(String RoleName);
}
